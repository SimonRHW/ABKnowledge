package com.simon.basic.core.extensions

import android.graphics.Color
import android.graphics.Typeface
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.text.getSpans

/**
 * @author Simon
 * @date 2021/9/10
 * @desc 文本下滑线、颜色、样式，删除线处理
 */


/**
 * 将一段文字中指定range的文字改变前景色
 * @param range 要改变前景色的文字的范围
 * @param color 要改变的颜色，默认是蓝色
 */
fun CharSequence.toColorSpan(
    range: IntRange,
    color: Int = Color.BLUE
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            ForegroundColorSpan(color),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将整段文字中指定range的文字改变前景色
 * @param color 要改变的颜色，默认是蓝色
 */
fun CharSequence.toColorSpan(
    color: Int = Color.BLUE
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            ForegroundColorSpan(color),
            0,
            this.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将一段文字中指定range的文字改变大小
 * @param range 要改变大小的文字的范围
 * @param scale 缩放值，大于1，则比其他文字大；小于1，则比其他文字小；默认不缩放
 */
fun CharSequence.toSizeSpan(
    range: IntRange,
    scale: Float = 1f
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            RelativeSizeSpan(scale),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将整段文字指定大小改变
 * @param scale 缩放值，大于1，则比其他文字大；小于1，则比其他文字小；默认不缩放
 */
fun CharSequence.toSizeSpan(scale: Float = 1f): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            RelativeSizeSpan(scale),
            0,
            this.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将一段文字中指定range的文字改变背景色
 * @param range 要改变背景色的文字的范围
 * @param color 要改变的颜色，默认是红色
 */
fun CharSequence.toBackgroundColorSpan(
    range: IntRange,
    color: Int = Color.RED
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            BackgroundColorSpan(color),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将一段文字中指定range的文字添加删除线
 * @param range 要添加删除线的文字的范围
 */
fun CharSequence.toStrikethroughSpan(range: IntRange): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            StrikethroughSpan(),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将一段文字中指定range的文字添加下划线
 * @param range 要添加删除线的文字的范围
 */
fun CharSequence.toUnderlineSpan(range: IntRange): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            UnderlineSpan(),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 整段文字设置下划线
 */
fun CharSequence.toUnderlineSpan(): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            UnderlineSpan(),
            0,
            this.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 将一段文字中指定range的文字添加颜色和点击事件
 * @param range 目标文字的范围
 */
fun CharSequence.toClickSpan(
    range: IntRange,
    color: Int = Color.RED,
    isUnderlineText: Boolean = false,
    clickListener: View.OnClickListener
): SpannableString {
    return SpannableString(this).apply {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                clickListener.onClick(widget)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = color
                ds.isUnderlineText = isUnderlineText
            }
        }
        setSpan(clickableSpan, range.first, range.last, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字添加style效果
 * @param range 目标文字的范围
 */
fun CharSequence.toStyleSpan(
    style: Int = Typeface.BOLD,
    range: IntRange
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            StyleSpan(style),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/** TextView的扩展 **/
fun TextView.sizeSpan(
    str: String = "",
    range: IntRange,
    scale: Float = 1.5f
): TextView {
    text = (if (str.isEmpty()) text else str).toSizeSpan(range, scale)
    return this
}

fun TextView.colorSpan(
    str: String = "",
    range: IntRange,
    color: Int = Color.RED
): TextView {
    text = (if (str.isEmpty()) text else str).toColorSpan(range, color)
    return this
}

fun TextView.backgroundColorSpan(
    str: String = "",
    range: IntRange,
    color: Int = Color.RED
): TextView {
    text = (if (str.isEmpty()) text else str).toBackgroundColorSpan(range, color)
    return this
}

fun TextView.strikethroughSpanSpan(
    str: String = "",
    range: IntRange
): TextView {
    text = (if (str.isEmpty()) text else str).toStrikethroughSpan(range)
    return this
}

fun TextView.clickSpan(
    str: String = "",
    range: IntRange,
    color: Int = Color.RED,
    isUnderlineText: Boolean = false,
    clickListener: View.OnClickListener
): TextView {
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT  // remove click bg color
    text =
        (if (str.isEmpty()) text else str).toClickSpan(range, color, isUnderlineText, clickListener)
    return this
}

fun TextView.styleSpan(
    str: String = "",
    range: IntRange,
    style: Int = Typeface.BOLD
): TextView {
    text = (if (str.isEmpty()) text else str).toStyleSpan(style = style, range = range)
    return this
}

fun String.toClickableHtml(
    @ColorInt color: Int,
    listener: (url: String) -> Unit
): Spanned {
    val html = Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    val builder = SpannableStringBuilder(html)

    html.getSpans<URLSpan>()
        .forEach {
            val start = builder.getSpanStart(it)
            val end = builder.getSpanEnd(it)
            val flags = builder.getSpanFlags(it)

            val clickable = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    listener.invoke(it.url)
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = color
                    ds.isUnderlineText = false
                }
            }
            builder.setSpan(clickable, start, end, flags)
            builder.removeSpan(it)
        }

    return builder
}



