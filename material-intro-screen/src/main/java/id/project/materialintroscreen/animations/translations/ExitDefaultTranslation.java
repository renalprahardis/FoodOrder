package id.project.materialintroscreen.animations.translations;

import android.support.annotation.FloatRange;
import android.view.View;

import id.project.materialintroscreen.R;
import id.project.materialintroscreen.animations.IViewTranslation;

public class ExitDefaultTranslation implements IViewTranslation {
    @Override
    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
        view.setTranslationY(percentage * view.getResources().getDimensionPixelOffset(R.dimen.y_offset));
    }
}
