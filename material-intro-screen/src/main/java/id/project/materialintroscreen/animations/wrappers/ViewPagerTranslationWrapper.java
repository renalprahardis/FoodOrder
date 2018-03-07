package id.project.materialintroscreen.animations.wrappers;

import android.view.View;

import id.project.materialintroscreen.animations.ViewTranslationWrapper;
import id.project.materialintroscreen.animations.translations.AlphaTranslation;
import id.project.materialintroscreen.animations.translations.DefaultAlphaTranslation;

public class ViewPagerTranslationWrapper extends ViewTranslationWrapper {
    public ViewPagerTranslationWrapper(View view) {
        super(view);

        setDefaultTranslation(new DefaultAlphaTranslation())
                .setExitTranslation(new AlphaTranslation());
    }
}