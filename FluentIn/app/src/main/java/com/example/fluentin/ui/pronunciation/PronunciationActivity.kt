package com.example.fluentin.ui.pronunciation

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.fluentin.R
import com.example.fluentin.databinding.ActivityPronunciationBinding
import com.example.fluentin.utils.ViewModelFactory
import com.example.fluentin.data.Result
import com.example.fluentin.data.UserSharedPreferences
import com.example.fluentin.ui.main.MainActivity
import java.util.Random

class PronunciationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPronunciationBinding

    private val viewModel: PronunciationViewModel by viewModels<PronunciationViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private var currentQuestionIndex = 0
    private var mediaPlayer: MediaPlayer? = null

    private var tvSpeak : TextView? = null
    private val LANGUAGE_ENGLISH = "en"

    private var currentDialog: AlertDialog? = null

    private var idAudioNative: Int? = null


    private val userSharedPreferences = UserSharedPreferences


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPronunciationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKategori.setOnClickListener {
            Toast.makeText(this, "Cooming Soon..!", Toast.LENGTH_SHORT).show()
        }

        tvSpeak = findViewById(R.id.tv_result_speak)


        binding.btnSound.setOnClickListener {
            playSound(currentQuestionIndex)
        }


        binding.btnReload.setOnClickListener {
            binding.btnReload.animate()
                .rotationBy(90f)
                .setDuration(500)
                .start()

            tvSpeak!!.setText("")
            currentQuestionIndex++
            getListNative()
        }
        getListNative()


        binding.btnMic.setOnClickListener(View.OnClickListener {
            val mic_google = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            mic_google.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            mic_google.putExtra(RecognizerIntent.EXTRA_LANGUAGE, LANGUAGE_ENGLISH)
            try {
                startActivityForResult(mic_google, RESULT_SPEECH)
                tvSpeak!!.setText("")
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Maaf, Device Kamu Tidak Support Speech To Text",
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        })


    }


    private fun playSound(questionIndex: Int) {
        viewModel.getNativeList().observe(this@PronunciationActivity) { result ->
            when (result) {
                is Result.Success -> {
                    val allNativeList = result.data

                    if (questionIndex < allNativeList.size) {
                        val currentQuestion = allNativeList[questionIndex]
                        val soundUrl = currentQuestion.audioPath

                        if (!soundUrl.isNullOrBlank()) {

                            releaseMediaPlayer()
                            mediaPlayer = MediaPlayer()
                            mediaPlayer?.setDataSource(soundUrl)
                            mediaPlayer?.prepare()
                            mediaPlayer?.start()
                        } else {
                            message("No sound available for this question.")
                        }
                    } else {
                        message("Invalid question index.")
                    }
                }

                is Result.Error -> {
                    message(result.error)
                }

                else -> {
                }
            }
        }
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        releaseMediaPlayer()
        super.onDestroy()
    }


    private fun getListNative() {
        viewModel.getNativeList().observe(this@PronunciationActivity) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }

                is Result.Success -> {
                    showLoading(false)
                    val allNativeList = result.data

                    if (currentQuestionIndex < allNativeList.size) {
                        val currentQuestion = allNativeList[currentQuestionIndex]

                        binding.tvEngglish.text = currentQuestion.text_audio
                        binding.tvIndonesia.text = currentQuestion.text_translate

                        idAudioNative = currentQuestion.native_audio_id

                        Log.d("DATA ID USER PRONOUN : ", "$idAudioNative")


                    } else {
                        message("No more questions.")
                        currentQuestionIndex--
                    }
                }

                is Result.Error -> {
                    showLoading(false)
                    message(result.error)
                }
            }
        }
    }


    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    private fun showDialog(dialogName: String, point: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView: View
        if (dialogName == "item_dialog_benar") {
            dialogView = inflater.inflate(R.layout.item_dialog_benar, null)

            val tvPoint = dialogView.findViewById<TextView>(R.id.tv_point)
            tvPoint.text = "+$point"

            dialogView.findViewById<Button>(R.id.btn_lanjutkan).setOnClickListener {
                currentDialog?.dismiss()

                tvSpeak!!.setText("")
                currentQuestionIndex++
                getListNative()

                savePoints(point)
            }
        } else {
            dialogView = inflater.inflate(R.layout.item_dialog_salah, null)
            dialogView.findViewById<Button>(R.id.btn_coba_lagi).setOnClickListener {
                currentDialog?.dismiss()
                getListNative()
            }
        }

        dialogBuilder.setView(dialogView)
        currentDialog = dialogBuilder.create()
        currentDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        currentDialog?.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RESULT_SPEECH -> if (resultCode == RESULT_OK && data != null) {
                val spokenText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0) ?: ""
                tvSpeak!!.text = spokenText

                val databaseText = binding.tvEngglish.text.toString()
                val sanitizedSpokenText = spokenText.replace("[.,]".toRegex(), "")
                val sanitizedDatabaseText = databaseText.replace("[.,]".toRegex(), "")


                if (sanitizedSpokenText.equals(sanitizedDatabaseText, ignoreCase = true)) {
                    val random = Random()
                    val randomPoint = random.nextInt(91) + 10
                    showDialog("item_dialog_benar", randomPoint)
                } else {
                    showDialog("item_dialog_salah", 0)
                }
            }
        }
    }

    private fun savePoints(points: Int) {
        val userId = userSharedPreferences.getUserId(this)
        val key = "user_points_$userId"

        val currentPoints = userSharedPreferences.getUserPoints(this, key)
        val newPoints = currentPoints + points

        userSharedPreferences.saveUserPoints(this, key, newPoints)

        // Panggil fungsi uploadRecord di sini
        val nativeAudioId = idAudioNative
        val skor = points.toString()

        viewModel.uploadRecord(userId, nativeAudioId.toString(), skor).observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    // Tampilkan pesan atau lakukan sesuatu jika upload berhasil
                }
                is Result.Error -> {
                    message(result.error)
                }
                is Result.Loading -> {
                    showLoading(true)
                }
            }
        }

    }
    

    companion object {
        protected const val RESULT_SPEECH = 1
    }


}
