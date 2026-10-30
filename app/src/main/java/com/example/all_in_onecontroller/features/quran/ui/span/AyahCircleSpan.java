package com.example.all_in_onecontroller.features.quran.ui.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AyahCircleSpan extends ReplacementSpan {

    private final int ayahNumber;
    private final String ayahArabicNumber;
    private final float density;
    private final Typeface font;
    private final float horizontalPadding;

    // Elegant Light Mushaf Colors (Matching the Surah Header Frame)
    private static final int FILL_COLOR = 0xFFF0FDF4;    // Subtle soft mint fill
    private static final int STROKE_COLOR = 0xFF0D9488;  // Vibrant teal / cyan stroke matching header
    private static final int TEXT_COLOR = 0xFF0F172A;    // Crisp dark slate Arabic numeral

    public AyahCircleSpan(int ayahNumber, float density, @Nullable Typeface font) {
        this.ayahNumber = ayahNumber;
        this.ayahArabicNumber = toArabicDigits(ayahNumber);
        this.density = density;
        this.font = font;
        this.horizontalPadding = 10f * density; // Clean, uniform 10dp margin on each side of the circle
    }

    public int getAyahNumber() {
        return ayahNumber;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        float diameter = getDiameter(paint);
        return (int) (diameter + horizontalPadding * 2);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, @NonNull Paint paint) {
        float diameter = getDiameter(paint);
        float radius = diameter / 2f;
        float centerX = x + horizontalPadding + radius;

        // Align circle vertically with the font ascent/descent
        Paint.FontMetrics fm = paint.getFontMetrics();
        float centerY = y + (fm.ascent + fm.descent) / 2f;

        // 1. Draw Subtle Light Filled Circle
        Paint fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(FILL_COLOR);
        canvas.drawCircle(centerX, centerY, radius, fillPaint);

        // 2. Draw Elegant Teal / Cyan Outline Stroke
        Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(1.4f * density);
        strokePaint.setColor(STROKE_COLOR);
        canvas.drawCircle(centerX, centerY, radius, strokePaint);

        // 3. Draw Centered Dark Arabic Numeral
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(TEXT_COLOR);
        textPaint.setTextAlign(Paint.Align.CENTER);
        if (font != null) {
            textPaint.setTypeface(font);
        } else {
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }

        // Adjust text size based on number of digits
        float textSize = ayahArabicNumber.length() > 2 ? radius * 0.85f : radius * 1.05f;
        textPaint.setTextSize(textSize);

        Rect bounds = new Rect();
        textPaint.getTextBounds(ayahArabicNumber, 0, ayahArabicNumber.length(), bounds);
        float textY = centerY - bounds.exactCenterY();

        canvas.drawText(ayahArabicNumber, centerX, textY, textPaint);
    }

    private float getDiameter(Paint paint) {
        float baseSize = paint.getTextSize() * 0.95f;
        float minDiameter = 24f * density;
        return Math.max(baseSize, minDiameter);
    }

    public static String toArabicDigits(int number) {
        char[] arabicDigits = {'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(number);
        for (int i = 0; i < str.length(); i++) {
            sb.append(arabicDigits[str.charAt(i) - '0']);
        }
        return sb.toString();
    }
}
