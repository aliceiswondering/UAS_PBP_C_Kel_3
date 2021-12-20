package com.patriciadevitasamara.uas_pyb.model.assignment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AssignmentItem implements Parcelable {

	@SerializedName("materi")
	private String materi;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("judul")
	private String judul;

	protected AssignmentItem(Parcel in) {
		materi = in.readString();
		updatedAt = in.readString();
		createdAt = in.readString();
		id = in.readInt();
		deskripsi = in.readString();
		judul = in.readString();
	}

	public static final Creator<AssignmentItem> CREATOR = new Creator<>() {
		@Override
		public AssignmentItem createFromParcel(Parcel in) {
			return new AssignmentItem(in);
		}

		@Override
		public AssignmentItem[] newArray(int size) {
			return new AssignmentItem[size];
		}
	};

	public void setMateri(String materi){
		this.materi = materi;
	}

	public String getMateri(){
		return materi;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(materi);
		parcel.writeString(updatedAt);
		parcel.writeString(createdAt);
		parcel.writeInt(id);
		parcel.writeString(deskripsi);
		parcel.writeString(judul);
	}
}