package com.example.inventrohyder.musicstructureapp;

/**
 * {@link Podcast} represents a podcast that the user may want to listen to.
 * It contains the image of the podcast, its title and its author.
 */
class Podcast {

    /**
     * The title of the podcast
     **/
    private String mTitle;

    /**
     * The author of the podcast
     **/
    private String mAuthor;

    /**
     * The image of the podcast
     **/
    private int mImageResourceId;

    /**
     * Creates a new Podcast object with a default image which is the application icon.
     *
     * @param Title  is the title of the Podcast
     * @param Author is the name of the author of the podcast
     */
    Podcast(String Title, String Author) {
        this(Title, Author, R.drawable.ic_launcher);
    }

    /**
     * Creates a new Podcast object.
     *
     * @param Title           is the title of the Podcast
     * @param Author          is the name of the author of the podcast
     * @param imageResourceId is the resource ID of the audio file for the podcast
     */
    Podcast(String Title, String Author, int imageResourceId) {
        mTitle = Title;
        mAuthor = Author;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the Title of the podcast
     */
    String getTitle() {
        return mTitle;
    }

    /**
     * Get the Author of the podcast
     */
    String getAuthor() {
        return mAuthor;
    }

    /**
     * Get the Image resource id of the podcast
     */
    int getImageResourceId() {
        return mImageResourceId;
    }
}
