//package rgp.com.shortreckonings.view;
//
///**
// * Created by khyagupt on 14-05-2016.
// */
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.drawable.Drawable;
//import android.util.AttributeSet;
//import android.widget.ImageView;
//
//public abstract class ShaderImageView extends ImageView {
//    private static final boolean DEBUG = false;
//    private ShaderHelper pathHelper;
//
//    protected abstract ShaderHelper createImageViewHelper();
//
//    public ShaderImageView(Context context) {
//        super(context);
//        setup(context, null, 0);
//    }
//
//    public ShaderImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        setup(context, attrs, 0);
//    }
//
//    public ShaderImageView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        setup(context, attrs, defStyle);
//    }
//
//    private void setup(Context context, AttributeSet attrs, int defStyle) {
//        getPathHelper().init(context, attrs, defStyle);
//    }
//
//    protected ShaderHelper getPathHelper() {
//        if (this.pathHelper == null) {
//            this.pathHelper = createImageViewHelper();
//        }
//        return this.pathHelper;
//    }
//
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (getPathHelper().isSquare()) {
//            int dimen = Math.min(getMeasuredWidth(), getMeasuredHeight());
//            setMeasuredDimension(dimen, dimen);
//        }
//    }
//
//    public void setImageBitmap(Bitmap bm) {
//        super.setImageBitmap(bm);
//        getPathHelper().onImageDrawableReset(getDrawable());
//    }
//
//    public void setImageDrawable(Drawable drawable) {
//        super.setImageDrawable(drawable);
//        getPathHelper().onImageDrawableReset(getDrawable());
//    }
//
//    public void setImageResource(int resId) {
//        super.setImageResource(resId);
//        getPathHelper().onImageDrawableReset(getDrawable());
//    }
//
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        getPathHelper().onSizeChanged(w, h);
//    }
//
//    @SuppressLint({"WrongCall"})
//    public void onDraw(Canvas canvas) {
//        if (!getPathHelper().onDraw(canvas)) {
//            super.onDraw(canvas);
//        }
//    }
//}