package com.qhhtofficial.qhht.module;

import android.os.Parcel;
import android.os.Parcelable;

public class Content implements Parcelable {

  private String title;
  private String english;
  private String chinese;
  private boolean hasSound;

  public Content(String title, String english, String chinese, boolean hasSound) {
    this.title = title;
    this.english = english;
    this.chinese = chinese;
    this.hasSound = hasSound;
  }

  protected Content(Parcel in) {
    english = in.readString();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getEnglish() {
    return english;
  }

  public String getChinese() {
    return chinese;
  }

  public boolean isHasSound() {
    return hasSound;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Content)) return false;

    Content content = (Content) o;

    if (isHasSound() != content.isHasSound()) return false;
    return getEnglish() != null ? getEnglish().equals(content.getEnglish()) : content.getEnglish() == null;

  }

  @Override
  public int hashCode() {
    int result = getEnglish() != null ? getEnglish().hashCode() : 0;
    result = 31 * result + (isHasSound() ? 1 : 0);
    return result;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(english);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Content> CREATOR = new Creator<Content>() {
    @Override
    public Content createFromParcel(Parcel in) {
      return new Content(in);
    }

    @Override
    public Content[] newArray(int size) {
      return new Content[size];
    }
  };
}

