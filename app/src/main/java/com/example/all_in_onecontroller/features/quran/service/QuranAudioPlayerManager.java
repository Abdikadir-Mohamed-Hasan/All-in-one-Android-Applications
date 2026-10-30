package com.example.all_in_onecontroller.features.quran.service;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.example.all_in_onecontroller.features.quran.model.Dars;

import java.io.IOException;

public class QuranAudioPlayerManager {

    public interface OnMediaStateChangeListener {
        void onPreparing();
        void onReady(int durationMs);
        void onPlaybackStarted();
        void onPlaybackPaused();
        void onPlaybackProgress(int currentPositionMs, int durationMs);
        void onError(String errorMessage);
        void onCompleted();
    }

    private static QuranAudioPlayerManager instance;
    private Context appContext;
    private MediaPlayer mediaPlayer;
    private Dars currentDars;
    private String currentSheikhName;
    private String currentSurahName;

    private boolean isPreparing = false;
    private boolean isLooping = false;
    private float currentSpeed = 1.0f;
    private OnMediaStateChangeListener listener;

    private final Handler progressHandler = new Handler(Looper.getMainLooper());
    private final Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                if (listener != null) {
                    listener.onPlaybackProgress(mediaPlayer.getCurrentPosition(), mediaPlayer.getDuration());
                }
                progressHandler.postDelayed(this, 500);
            }
        }
    };

    private QuranAudioPlayerManager() {}

    public static synchronized QuranAudioPlayerManager getInstance() {
        if (instance == null) {
            instance = new QuranAudioPlayerManager();
        }
        return instance;
    }

    public void setContext(Context context) {
        this.appContext = context.getApplicationContext();
    }

    public void setListener(OnMediaStateChangeListener listener) {
        this.listener = listener;
    }

    public void playDars(Dars dars, String sheikhName, String surahName) {
        this.currentDars = dars;
        this.currentSheikhName = sheikhName;
        this.currentSurahName = surahName;

        releaseMediaPlayer();

        if (dars == null || dars.getAudioUrl() == null) {
            if (listener != null) {
                listener.onError("Audio URL is invalid or unavailable.");
            }
            return;
        }

        try {
            if (listener != null) {
                listener.onPreparing();
            }

            isPreparing = true;
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );

            String audioUrl = dars.getAudioUrl();
            if (audioUrl != null && audioUrl.startsWith("asset://") && appContext != null) {
                // Load from local asset file
                String assetPath = audioUrl.substring("asset://".length());
                try {
                    AssetFileDescriptor afd = appContext.getAssets().openFd(assetPath);
                    mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    afd.close();
                } catch (IOException e) {
                    isPreparing = false;
                    if (listener != null) {
                        listener.onError("Local audio file not found: " + assetPath);
                    }
                    return;
                }
            } else {
                mediaPlayer.setDataSource(audioUrl);
            }
            mediaPlayer.setLooping(isLooping);

            mediaPlayer.setOnPreparedListener(mp -> {
                isPreparing = false;
                if (listener != null) {
                    listener.onReady(mp.getDuration());
                }
                applySpeed(currentSpeed);
                mp.start();
                if (listener != null) {
                    listener.onPlaybackStarted();
                }
                startProgressUpdate();
            });

            mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                isPreparing = false;
                if (listener != null) {
                    listener.onError("Error playing audio stream (code " + what + "). Tap to retry.");
                }
                return true;
            });

            mediaPlayer.setOnCompletionListener(mp -> {
                progressHandler.removeCallbacks(progressRunnable);
                if (listener != null) {
                    listener.onCompleted();
                }
            });

            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            isPreparing = false;
            if (listener != null) {
                listener.onError("Network error preparing audio: " + e.getLocalizedMessage());
            }
        }
    }

    public void togglePlayPause() {
        if (mediaPlayer == null) return;

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            progressHandler.removeCallbacks(progressRunnable);
            if (listener != null) {
                listener.onPlaybackPaused();
            }
        } else {
            mediaPlayer.start();
            if (listener != null) {
                listener.onPlaybackStarted();
            }
            startProgressUpdate();
        }
    }

    public void seekTo(int positionMs) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(positionMs);
        }
    }

    public void seekBy(int offsetMs) {
        if (mediaPlayer != null) {
            try {
                int current = mediaPlayer.getCurrentPosition();
                int duration = mediaPlayer.getDuration();
                int target = Math.max(0, Math.min(duration, current + offsetMs));
                mediaPlayer.seekTo(target);
                if (listener != null) {
                    listener.onPlaybackProgress(target, duration);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPlaybackSpeed(float speed) {
        this.currentSpeed = speed;
        applySpeed(speed);
    }

    private void applySpeed(float speed) {
        if (mediaPlayer != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public float getPlaybackSpeed() {
        return currentSpeed;
    }

    public void toggleLooping() {
        isLooping = !isLooping;
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(isLooping);
        }
    }

    public boolean isLooping() { return isLooping; }
    public boolean isPlaying() { return mediaPlayer != null && mediaPlayer.isPlaying(); }
    public boolean isPreparing() { return isPreparing; }
    public Dars getCurrentDars() { return currentDars; }
    public String getCurrentSheikhName() { return currentSheikhName; }
    public String getCurrentSurahName() { return currentSurahName; }

    public int getCurrentPosition() {
        return mediaPlayer != null ? mediaPlayer.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return mediaPlayer != null ? mediaPlayer.getDuration() : 0;
    }

    private void startProgressUpdate() {
        progressHandler.removeCallbacks(progressRunnable);
        progressHandler.post(progressRunnable);
    }

    private void releaseMediaPlayer() {
        progressHandler.removeCallbacks(progressRunnable);
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.reset();
                mediaPlayer.release();
            } catch (Exception ignored) {}
                mediaPlayer = null;
        }
    }

    public void stopAndRelease() {
        releaseMediaPlayer();
        currentDars = null;
    }
}
