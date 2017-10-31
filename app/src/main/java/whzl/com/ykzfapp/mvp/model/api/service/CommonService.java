/**
  * Copyright 2017 JessYan
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package whzl.com.ykzfapp.mvp.model.api.service;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.CommunityBean;
import whzl.com.ykzfapp.bean.DictionaryBean;
import whzl.com.ykzfapp.bean.FollowListBean;
import whzl.com.ykzfapp.bean.HouseDetailBean;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.bean.SignBean;

/**
 * ================================================
 * 存放通用的一些 API
 * <p>
 * Created by JessYan on 08/05/2016 12:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface CommonService {



    @GET("ykzfInterface/dictionaryAction_listDictionary.do")
    Observable<BaseEntity<List<DictionaryBean>>> getDictionary();

    //获取全部小区
    @GET("ykzfInterface/communityAction_listCommunity.do?community.name=&region=")
    Observable<BaseEntity<List<CommunityBean>>> getCommunity();

    //上传文件
    @POST("ykzfInterface/uploadAction_doUpload.do")
    Observable<BaseEntity<String>> upLoadFiles(@Body MultipartBody myfiles);

    //添加跟进
    @POST("ykzfInterface/followUpAction_addFollowUp.do")
    Observable<BaseEntity<FollowListBean>> addFollowUp(@Query("loginUserName")String loginName,
                                               @Query("loginUserPwd")String loginPwd,
                                               @Query("followUp.houseId")String houseId,
                                               @Query("followContent")String followContent);

    //添加房源状态
    @POST("ykzfInterface/houseAction_infoStateUpdate.do")
    Observable<BaseEntity<HouseListBean>> infoStateUpdate(@Query("loginName")String loginName,
                                                       @Query("loginPwd")String loginPwd,
                                                       @Query("house.id")String houseId,
                                                       @Query("house.infoState")String followContent,
                                                       @Query("house.infoStateInfo")String info);

    //签到
    @POST("ykzfInterface/signAction_addSign.do")
    Observable<BaseEntity<SignBean>> sign(@Query("loginUserName")String loginName,
                                          @Query("loginUserPwd") String loginPwd,
                                          @Query("signCommunityId")int signCommunityId,
                                          @Query("sign.signInfo")String signInfo);


    //更新房源
    @PUT("ykzfInterface/houseAction_updateHouse.do")
    Observable<BaseEntity<HouseListBean>> updataHouse(
            @Query("loginName") String loginName,
            @Query("loginPwd") String loginPwd,
            @Query("house.id") String id,
            @Query("house.title") String title,
            @Query("house.community.id") String Community,
            @Query("house.buildNo") String buildNo,
            @Query("house.unitNo") String unitNo,
            @Query("house.roomNo") String roomNo,
            @Query("house.bedRooms") String bedRooms,
            @Query("house.livingRooms") String livingRooms,
            @Query("house.cookRooms") String cookRooms,
            @Query("house.bathRooms") String bathRooms,
            @Query("fyPath") String fyPath,
            @Query("fyOutPath") String fyOutPath,
            @Query("hxPath") String hxPath,
            @Query("vdoPath") String vdoPath,
            @Query("voicePath") String voicePath
    );

    @GET("ykzfInterface/houseAction_detailHouse.do")
    Observable<BaseEntity<HouseDetailBean>> getHouseDetail(@Query("house.id") String id);

    @GET("ykzfInterface/followUpAction_listFollowUp.do")
    Observable<BaseEntity<List<FollowListBean>>> getFollowList(@Query("followUp.houseId") String id);

    @GET("ykzfInterface/houseAction_listHouseByUser.do")
    Observable<BaseEntity<List<HouseListBean>>> listHouseByUser(@Query("loginName") String name,@Query("loginPwd")  String password,@Query("pageNum") String pageNum,@Query("rows")  String rows);

    @GET("ykzfInterface/houseAction_listHouseForAgent.do")
    Observable<BaseEntity<List<HouseListBean>>> listHouseForAgent(@Query("loginName") String name,@Query("loginPwd")  String password,@Query("pageNum") String pageNum,@Query("rows")  String rows);

    @GET("ykzfInterface/houseAction_listHouse.do")
    Observable<BaseEntity<List<HouseListBean>>> listHouse(
            @Query("title") String title,@Query("region")  String region,
            @Query("salePrice") String salePrice,@Query("bedRooms")  String bedRooms,
            @Query("area") String area,
            @Query("pageNum") String pageNum,@Query("rows")  String rows);

}
