package com.example.inventrohyder.musicstructureapp;

/**
 * {@link Podcast} represents a {@link Podcast} that the user may want to listen to.
 * It contains the image of the {@link Podcast}, its title and its author.
 */
class Podcast {

    /**
     * The title of the {@link Podcast}
     **/
    private String mTitle;

    /**
     * The author of the {@link Podcast}
     **/
    private String mAuthor;

    /**
     * The image of the {@link Podcast}
     **/
    private int mImageResourceId;

    /**
     * Creates a new {@link Podcast} object with a default image which is the application icon.
     *
     * @param Title  is the title of the {@link Podcast}
     * @param Author is the name of the author of the {@link Podcast}
     */
    Podcast(String Title, String Author) {
        this(Title, Author, R.drawable.ic_play_circle_outline_white_120dp);
    }

    /**
     * Creates a new {@link Podcast} object.
     *
     * @param Title           is the title of the {@link Podcast}
     * @param Author          is the name of the author of the {@link Podcast}
     * @param imageResourceId is the resource ID of the audio file for the {@link Podcast}
     */
    Podcast(String Title, String Author, int imageResourceId) {
        mTitle = Title;
        mAuthor = Author;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the Title of the {@link Podcast}
     */
    String getTitle() {
        return mTitle;
    }

    /**
     * Get the Author of the {@link Podcast}
     */
    String getAuthor() {
        return mAuthor;
    }

    /**
     * Get the Image resource id of the {@link Podcast}
     */
    int getImageResourceId() {
        return mImageResourceId;
    }
}
