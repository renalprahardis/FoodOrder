package id.project.materialintroscreen.animations.wrappers;

import android.view.View;

import id.project.materialintroscreen.R;
import id.project.materialintroscreen.animations.ViewTranslationWrapper;
import id.project.materialintroscreen.animations.translations.DefaultPositionTranslation;
import id.project.materialintroscreen.animations.translations.ExitDefaultTranslation;

public class NextButtonTranslationWrapper extends ViewTranslationWrapper {
    public NextButtonTranslationWrapper(View view) {
        super(view);

        setExitTranslation(new ExitDefaultTranslation())
                .setDefaultTranslation(new DefaultPositionTranslation())
                .setErrorAnimation(R.anim.shake_it);
    }
}