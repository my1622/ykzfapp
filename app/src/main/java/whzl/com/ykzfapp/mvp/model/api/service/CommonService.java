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
import retrofit2.http.GET;
import retrofit2.http.Query;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.DictionaryBean;
import whzl.com.ykzfapp.bean.HouseListBean;

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

    @GET("ykzfInterface/houseAction_listHouseByUser.do")
    Observable<BaseEntity<List<HouseListBean>>> listHouseByUser(@Query("name") String name,@Query("password")  String password,@Query("pageNum") String pageNum,@Query("rows")  String rows);

}
