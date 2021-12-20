package com.patriciadevitasamara.uas_pyb.model.course;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CourseItem implements Parcelable {

	@SerializedName("nama_kelas")
	private String namaKelas;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kode")
	private String kode;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("kapasitas")
	private int kapasitas;

	@SerializedName("mata_pelajaran")
	private String mataPelajaran;

	protected CourseItem(Parcel in) {
		namaKelas = in.readString();
		updatedAt = in.readString();
		kode = in.readString();
		createdAt = in.readString();
		id = in.readInt();
		kapasitas = in.readInt();
		mataPelajaran = in.readString();
	}

	public static final Creator<CourseItem> CREATOR = new Creator<>() {
		@Override
		public CourseItem createFromParcel(Parcel in) {
			return new CourseItem(in);
		}

		@Override
		public CourseItem[] newArray(int size) {
			return new CourseItem[size];
		}
	};

	public void setNamaKelas(String namaKelas){
		this.namaKelas = namaKelas;
	}

	public String getNamaKelas(){
		return namaKelas;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
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

	public void setKapasitas(int kapasitas){
		this.kapasitas = kapasitas;
	}

	public String getKapasitas(){
		return String.valueOf(kapasitas);
	}

	public void setMataPelajaran(String mataPelajaran){
		this.mataPelajaran = mataPelajaran;
	}

	public String getMataPelajaran(){
		return mataPelajaran;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(namaKelas);
		parcel.writeString(updatedAt);
		parcel.writeString(kode);
		parcel.writeString(createdAt);
		parcel.writeInt(id);
		parcel.writeInt(kapasitas);
		parcel.writeString(mataPelajaran);
	}
}