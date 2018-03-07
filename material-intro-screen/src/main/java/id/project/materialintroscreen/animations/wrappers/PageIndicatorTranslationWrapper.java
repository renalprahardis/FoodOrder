package id.project.materialintroscreen.animations.wrappers;

import android.view.View;

import id.project.materialintroscreen.animations.ViewTranslationWrapper;
import id.project.materialintroscreen.animations.translations.DefaultPositionTranslation;
import id.project.materialintroscreen.animations.translations.ExitDefaultTranslation;

public class PageIndicatorTranslationWrapper extends ViewTranslationWrapper {
    public PageIndicatorTranslationWrapper(View view) {
        super(view);

        setDefaultTranslation(new DefaultPositionTranslation())
                .setExitTranslation(new ExitDefaultTranslation());
    }
}