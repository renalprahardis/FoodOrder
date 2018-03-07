package id.project.materialintroscreen.listeners.scrollListeners;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import id.project.materialintroscreen.SlideFragment;
import id.project.materialintroscreen.adapter.SlidesAdapter;
import id.project.materialintroscreen.listeners.IPageScrolledListener;
import id.project.materialintroscreen.parallax.Parallaxable;

public class ParallaxScrollListener implements IPageScrolledListener {
    private SlidesAdapter adapter;

    public ParallaxScrollListener(SlidesAdapter adapter) {
        this.adapter = adapter;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void pageScrolled(int position, float offset) {
        if (position != adapter.getCount()) {
            Fragment fragment = adapter.getItem(position);
            Fragment fragmentNext = getNextFragment(position);

            if (fragment != null && fragment instanceof Parallaxable) {
                ((Parallaxable) fragment).setOffset(offset);
            }

            if (fragmentNext != null && fragment instanceof Parallaxable) {
                ((Parallaxable) fragmentNext).setOffset(offset - 1);
            }
        }
    }

    @Nullable
    private SlideFragment getNextFragment(int position) {
        if (position < adapter.getLastItemPosition()) {
            return adapter.getItem(position + 1);
        }
        return null;
    }
}