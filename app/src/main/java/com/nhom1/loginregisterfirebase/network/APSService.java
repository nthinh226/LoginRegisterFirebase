package com.nhom1.loginregisterfirebase.network;


import com.nhom1.loginregisterfirebase.models.LoginModal;
import com.nhom1.loginregisterfirebase.models.LoginResponse;
import com.nhom1.loginregisterfirebase.models.Post;
import com.nhom1.loginregisterfirebase.models.RegisterModal;
import com.nhom1.loginregisterfirebase.models.RegisterResponse;
import com.nhom1.loginregisterfirebase.models.Response;
import com.nhom1.loginregisterfirebase.models.TrainingFaceModal;
import com.nhom1.loginregisterfirebase.models.TrainingResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APSService {
    @GET("hello")
    Call<String> hello();

    @FormUrlEncoded
    @POST("add")
    Call<Response> getResult(
            @Field("sothunhat") int sothunhat,
            @Field("sothuhai") int sothuhai);

    @POST("api/v1/register")
    Call<RegisterResponse> registerUser(@Body RegisterModal registerModal);

    @POST("api/v1/loginByAccount")
    Call<LoginResponse> login(@Body LoginModal loginModal);

    @POST("api/v1/training")
    Call<TrainingResponse> trainingFace(@Body TrainingFaceModal listBase64);

    @POST("api/v1/loginByFace")
    Call<TrainingResponse> loginByFace(@Body TrainingFaceModal listBase64);


    /*

    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<UserResponse> login(
            @Field("event") String event,
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<UpdatePassResponse> updatePass(
            @Field("event") String event,
            @Field("iduser") String iduser,
            @Field("pass") String pass);

    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<LandResponse> getDSLand(
            @Field("event") String event, @Field("iduser") String iduser,
            @Field("permission") String permission,
            @Field("record") int record,
            @Field("page") int page);

    //lay ds ho nong dan
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<HoNongDanResponse> getDSHoNongDan(@Field("event") String event);

    //lay ds tinh - thanh pho
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<ProvinceResponse> getDSProvince(@Field("event") String event);

    //lay ds quan - huyen
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<DistrictResponse> getDSDistrict(
            @Field("event") String event,
            @Field("id") String id);

    //lay ds xa - phuong
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<WardsResponse> getDSWard(
            @Field("event") String event,
            @Field("id") String id);

    //lay ds product parent
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<ProductParentResponse> getDSProductParent(@Field("event") String event);

    //lay ds product children
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<ProductChildResponse> getDSProductChild(
            @Field("event") String event,
            @Field("idpt_parent") String idpt_parent);

    //lay ds group product
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<GroupProductResponse> getDSGroupProduct(@Field("event") String event);

    //lay ds nha cung cap
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<NhaCungCapResponse> getDSNhaCungCap(@Field("event") String event);

    //lay ds don vi
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<UnitResponse> getDSUnit(@Field("event") String event);

    //upload avatar
    @Multipart
    @POST("spuploadimagestatus/process.php")
    Call<UploadImageResponse> uploadImage(
            @Part MultipartBody.Part image);

    //thay doi avatar
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<ChangeAvatarResponse> changeAvatar(
            @Field("event") String event,
            @Field("id_user") String id_user,
            @Field("avartar") String avatar);

    //them vung trong
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<InsertVungTrongResponse> insertVungTrong(
            @Field("event") String event,
            @Field("codeland") String codeland,
            @Field("nameland") String nameland,
            @Field("addressland") String addressland,
            @Field("area") String area,
            @Field("latland") String latland,
            @Field("lngland") String lngland,
            @Field("iduser") String iduser,
            @Field("imgland") String imgland,
            @Field("idprovince") String idprovince,
            @Field("iddistrict") String iddistrict,
            @Field("idward") String idward);

    //lay ds vung trong ke hoach trong
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<DSVungTrongKeHoachTrongResponse> getDSVungTrongKeHoachTrong(
            @Field("event") String event,
            @Field("page") int page,
            @Field("record") int record,
            @Field("search") String search,
            @Field("permission") String permission,
            @Field("iduser") String iduser,
            @Field("find") int find);

    //them vung trong ke hoach trong
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<InsertPlanResponse> insertPlanLand(
            @Field("event") String event,
            @Field("idland") String idland,
            @Field("idpr") String idpr,
            @Field("unit") String unit,
            @Field("mancc") String mancc,
            @Field("activeland") String activeland,
            @Field("quantity") String quantity,
            @Field("realarea") String realarea,
            @Field("begin_date") String begin_date,
            @Field("end_date") String end_date,
            @Field("codeplan") String codeplan,
            @Field("serial") String serial);

    //lay ds tat ca san pham
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<DSProductIdResponse> getAllProductId(
            @Field("event") String event,
            @Field("idpt") String idland);

    //tao qr code
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<QRCodeResponse> createQRCode(@Field("event") String event);

    //lay ds cong viec
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<WorkResponse> getALLWork(
            @Field("event") String event,
            @Field("page") int page,
            @Field("record") int record,
            @Field("search") String search);

    //them cong viec
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<InsertWorkResponse> insertWork(
            @Field("event") String event,
            @Field("idw") String idw,
            @Field("idplan") String idplan,
            @Field("orderjob") String orderjob,
            @Field("iduserthaotac") String iduserthaotac);
    //them chi tiet cong viec

    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<InsertDetailWorkResponse> insertDetailWork(
            @Field("event") String event,
            @Field("iduser") String iduser,
            @Field("idw") String idw,
            @Field("idplan") String idplan,
            @Field("date_work") String date_work,
            @Field("timecurrentwork") String timecurrentwork,
            @Field("content") String content,
            @Field("charge") String charge,
            @Field("listhinh") String listhinh);

    //thay doi trang thai
    //event=updatePlanLandReal&idplan=58&activeland=2&dateendreal=2022-04-27+00%3A00%3A00&quantityreal=30&unitreal=Con
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<ThayDoiTrangThaiChuoiResponse> thayDoiTrangThaiChuoi(
            @Field("event") String event,
            @Field("idplan") String idplan,
            @Field("activeland") int activeland,
            @Field("dateendreal") String dateendreal,
            @Field("quantityreal") String quantityreal,
            @Field("unitreal") String unitreal);

    //lay tat ca danh sach tham gia chuoi
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<AllJointStringResponse> getAllJointString(
            @Field("event") String event,
            @Field("page") int page,
            @Field("record") int record,
            @Field("search") String search,
            @Field("idchuoi") int idchuoi);
    //them tham gia chuoi
    @FormUrlEncoded
    @POST("php/api_aps.php")
    Call<InsertJoinStringResponse> insertJoinString(
            @Field("event") String event,
            @Field("ma") int ma,
            @Field("idplan") String idplan,
            @Field("type") int type,
            @Field("thoigian") long thoigian);

    @FormUrlEncoded
    @POST("apituan.php")
    Call<InsertResponse> insertDiemDanh(@Field("event") String event, @Field("iduser") String idUser,
                                        @Field("idkey") String idKey, @Field("lat") Double lat,
                                        @Field("lng") Double lng, @Field("ngaydiemdanh") String date,
                                        @Field("mamon") String maMon, @Field("magv") String maGv,
                                        @Field("idhocky") String idHocKy, @Field("idclass") String idClass);

    @FormUrlEncoded
    @POST("allapi.php")
    Call<DsDiemDanhResponse> getDiemDanh(@Field("event") String event, @Field("page") Integer page,
                                         @Field("record") Integer record, @Field("mamon") String idClass,
                                         @Field("iduser") String idUser);

    @FormUrlEncoded
    @POST("allapi.php")
    Call<ThongbaoResponse> getDSNotify(@Field("event") String event, @Field("page") Integer page,
                                       @Field("record") Integer record);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<UpdateLopResponse> UpdateClass(@Field("event") String event, @Field("id") String id,
                                        @Field("name") String name, @Field("idcourse") String idcourse,
                                        @Field("makhoa") String makhoa);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<UpdateBoMonResponse> UpdateBoMon(@Field("event") String event, @Field("idbomon") String idbomon,
                                          @Field("tenbomon") String tenbomon, @Field("khoa") String khoa);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<UpdateAvatar> UpdateAvatar(@Field("event") String event, @Field("avartar") String avartar,
                                          @Field("iduser") String iduser);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<UpdateMonHocResponse> update(@Field("event") String event, @Field("id_mon") String id_mon,
                                      @Field("sotc") String sotc, @Field("tenmon") String tenmon,
                                      @Field("mamon") String mamon, @Field("file_decuong") String file_decuong,
                                      @Field("idbomon") String idbomon, @Field("urlanh") String urlanh);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<UpdateCourseResponse> UpdateCourse(@Field("event") String event, @Field("id") String id,
                                            @Field("name") String name, @Field("nienkhoa") String nienkhoa);

    @FormUrlEncoded
    @POST("allapi.php")
    Call<DanhgiaRoot> getDSCauHoiAnswerDanhGiaSV(@Field("event") String event, @Field("mamon") String maMon,
                                                 @Field("makhoa") String maKhoa, @Field("idhocky") String idHocKy,
                                                 @Field("idcourse") String idCourse, @Field("idclass") String idClass);

    @FormUrlEncoded
    @POST("apituan.php")
    Call<InsertDanhGiaResponse> insertDanhGia(@Field("event") String event, @Field("masv") String maSv,
                                              @Field("idkey") String idKey, @Field("mamon") String maMon,
                                              @Field("magv") String maGv, @Field("idhocky") String idHocKy,
                                              @Field("idcauhoi") String idCauHoi, @Field("dapan") String dapan,
                                              @Field("ngaytaodanhgia") String date);

    @FormUrlEncoded
    @POST("allapi.php")
    Call<InsertLienHeResponse> InsertLienHe(@Field("event") String event, @Field("hoten") String hoTen,
                                            @Field("email") String email, @Field("sodt") String sodt,
                                            @Field("chude") String chuDe, @Field("noidung") String noiDung);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<KhoaRespone> getDSKhoa(@Field("event") String event, @Field("record") Integer record,
                                @Field("page") Integer page);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<BomonResponse> getDSBoMonALL(@Field("event") String event, @Field("record") Integer record,
                                      @Field("page") Integer page, @Field("khoa") String khoa);
    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<BomonResponse> getDSBoMon(@Field("event") String event, @Field("record") Integer record,
                                      @Field("page") Integer page, @Field("makhoa") String makhoa);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DSMonHocBoMonResponse> getDSMonHocBoMon(@Field("event") String event, @Field("record") Integer record,
                                                 @Field("page") Integer page, @Field("idbomon") String idbomon);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DSMonHocALLResponse> getDSMonHoc(@Field("event") String event, @Field("record") Integer record,
                                          @Field("page") Integer page, @Field("idbomon") String idbomon);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DeleteBoMonResponse> DeleteBoMon(@Field("event") String event, @Field("idbomon") String idbomon);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DeleteClassResponse> DeleteClass(@Field("event") String event, @Field("id") String id);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DeleteCourseResponse> DeleteCourse(@Field("event") String event, @Field("id") String id);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DeleteMonHocResponse> delete(@Field("event") String event, @Field("mamon") String mamon,
                                      @Field("filelinkanh") String filelinkanh, @Field("filelinkdc") String filelinkdc);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DeleteNotifyResponse> DeleteNotify(@Field("event") String event, @Field("id") String id,
                                            @Field("filelink") String filelink);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DeleteLienHeResponse> deleteLienHe(@Field("event") String event, @Field("id") String id);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DeletePhanCongMH> deletePhanCongMH(@Field("event") String event, @Field("idkey") String idkey);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<KhoasResponse> getDSCourse(@Field("event") String event, @Field("record") Integer record,
                                    @Field("page") Integer page);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<HockyResponse> getDSHK(@Field("event") String event, @Field("record") Integer record,
                                @Field("page") Integer page);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<InsertBoMonResponse> insertBoMon(@Field("event") String event, @Field("tenbomon") String tenbomon,
                                          @Field("khoa") String khoa);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<InsertMonHocResponse> insert(@Field("event") String event, @Field("id_mon") String id_mon,
                                      @Field("sotc") String sotc, @Field("tenmon") String tenmon,
                                      @Field("file_decuong") String file_decuong, @Field("idbomon") String idbomon,
                                      @Field("urlanh") String urlanh);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DSClassResponse> getDSClass(@Field("event") String event, @Field("record") Integer record,
                                     @Field("page") Integer page);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DSGiangVienPCResponse> getDSGiangVienPhanCongHK(@Field("event") String event, @Field("mamon") String mamon);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DSMonHocPCHKResponse> getDSMonHocPhanCongHK(@Field("event") String event, @Field("idhocky") String idhocky,
                                                     @Field("idclass") String idclass);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DSMonHocPCResponse> getDSMonHocPC(@Field("event") String event, @Field("record") Integer record,
                                           @Field("page") Integer page, @Field("hocky") String hocky,
                                           @Field("idclass") String idclass, @Field("magv") String magv,
                                           @Field("makhoa") String makhoa,@Field("mamon") String mamon,
                                           @Field("idcourse") String idcourse);

    @FormUrlEncoded
    @POST("admin_users.php")
    Call<DSMonHocDiemDanhResponse> getDSMonHocDiemDanh(@Field("event") String event, @Field("page") Integer page,
                                                       @Field("record") Integer record, @Field("mamon") String mamon,
                                                       @Field("idclass") String idclass, @Field("idhocky") String idhocky);

    @FormUrlEncoded
    @POST("allapi.php")
    Call<ItemDanhGiaResponse> getDSCauHoiAnswerDanhGiaSV_(@Field("event") String event, @Field("mamon") String mamon,
                                                          @Field("makhoa") String makhoa, @Field("idhocky") String idhocky,
                                                          @Field("course") String course, @Field("idclass") String idclass);
    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<InsertClassResponse> insertClass(@Field("event") String event, @Field("name") String name,
                                          @Field("idcourse") String idcourse, @Field("makhoa") String makhoa);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<InsertKhoasResponse> insertCourse(@Field("event") String event, @Field("name") String name,
                                           @Field("nienkhoa") String nienkhoa);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<InsertNotifySV> insertNotifySV(@Field("event") String event, @Field("content") String content,
                                        @Field("attachfile") String attachfile, @Field("isnew") String isnew,
                                        @Field("iduser") String iduser, @Field("type") String type);
    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<InsertPhanCongMonResponse> insertPhanCongMon(@Field("event") String event, @Field("mamon") String mamon,
                                                      @Field("idclass") String idclass, @Field("idhocky") String idhocky,
                                                      @Field("magv") String magv, @Field("idcourse") String idcourse,
                                                      @Field("makhoa") String makhoa);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<DSLienHeResponse> getDSLienHe(@Field("event") String event, @Field("record") Integer record,
                                       @Field("page") Integer page, @Field("s") String s);

    @FormUrlEncoded
    @POST("admin_monhoc.php")
    Call<InsertMonHocResponse> insert(@Field("event") String event, @Field("id_mon") String id_mon,
                                      @Field("sotc") Integer sotc, @Field("tenmon") String tenmon,
                                      @Field("file_decuong") String file_decuong, @Field("idbomon") String idbomon,
                                      @Field("urlanh") String urlanh);

    @FormUrlEncoded
    @POST("admin_users.php")
    Call<DSGiangVienResponse> getDSUsersGiangVien(@Field("event") String event,@Field("makhoa") String makhoa,
                                                  @Field("idbomon") String idbomon, @Field("p") Integer p,
                                                  @Field("find") String find, @Field("record") Integer record,
                                                  @Field("page") Integer page);

    @FormUrlEncoded
    @POST("admin_users.php")
    Call<InsertUsersResponse> InsertUser(@Field("event") String event, @Field("username") String username,
                                         @Field("phone") String phone, @Field("sex") String sex,
                                         @Field("dateofbirth") String dateofbirth, @Field("address") String address,
                                         @Field("detailname") String detailname, @Field("avartar") String avartar,
                                         @Field("idclass") String idclass, @Field("idbomon") String idbomon,
                                         @Field("makhoa") String makhoa, @Field("email") String email,
                                         @Field("permission") String permission);
    @FormUrlEncoded
    @POST("admin_users.php")
    Call<UpdateUser> UpdateUser(@Field("event") String event, @Field("iduser") Integer iduser,
                                @Field("username") Integer username, @Field("phone") String phone,
                                @Field("sex") String sex, @Field("dateofbirth") String dateofbirth,
                                @Field("address") String address, @Field("detailname") String detailname,
                                @Field("avartar") String avartar, @Field("idclass") String idclass,
                                @Field("idbomon") String idbomon, @Field("makhoa") String makhoa,
                                @Field("email") String email, @Field("permission") String permission);

    @FormUrlEncoded
    @POST("admin_users.php")
    Call<DSClassByCourseResponse> getDSClassByCourse(@Field("event") String event, @Field("record") Integer record,
                                                     @Field("page") Integer page, @Field("idcourse") String idcourse,
                                                     @Field("makhoa") String makhoa);

    @FormUrlEncoded
    @POST("admin_users.php")
    Call<UpdatePassResponse> UpdatePass(@Field("event") String event, @Field("iduser") String iduser,
                                        @Field("pass") String pass);
    @FormUrlEncoded
    @POST("admin_users.php")
    Call<DSSinhVienResponse> getDSUsers(@Field("event") String event, @Field("idclass") String idclass,
                                        @Field("p") Integer p, @Field("find") String find,
                                        @Field("record") Integer record, @Field("page") Integer page);

    @FormUrlEncoded
    @POST("qrcode.php")
    Call<DSSVDiemDanhPhienResponse> getListDiemDanhQR(@Field("event") String event, @Field("page") Integer page,
                                                      @Field("record") Integer record, @Field("id") String id);

    @FormUrlEncoded
    @POST("qrcode.php")
    Call<ListQRMonResponse> getListQRMon(@Field("event") String event, @Field("page") Integer page,
                                         @Field("record") Integer record, @Field("mamon") String mamon,
                                         @Field("idhocky") String idhocky, @Field("idclass") String idclass,
                                         @Field("magv") String magv);

    @FormUrlEncoded
    @POST("qrcode.php")
    Call<ListQRSVResponse> getListQRSV(@Field("event") String event, @Field("idclass") String idclass,
                                       @Field("idqr") String idqr);
    */
}