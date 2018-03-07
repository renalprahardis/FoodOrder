package id.project.materialintroscreen.animations.wrappers;

import android.view.View;

import id.project.materialintroscreen.animations.ViewTranslationWrapper;
import id.project.materialintroscreen.animations.translations.DefaultPositionTranslation;
import id.project.materialintroscreen.animations.translations.EnterDefaultTranslation;
import id.project.materialintroscreen.animations.translations.ExitDefaultTranslation;

public class BackButtonTranslationWrapper extends ViewTranslationWrapper {
    public BackButtonTranslationWrapper(View view) {
        super(view);

        setEnterTranslation(new EnterDefaultTranslation())
                .setDefaultTranslation(new DefaultPositionTranslation())
                .setExitTranslation(new ExitDefaultTranslation());
    }
}