package com.example.jyxmyt.http;


import com.example.jyxmyt.entity.BookingInfo;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.EveryDay;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.FingerReq;
import com.example.jyxmyt.entity.FoodStandard;
import com.example.jyxmyt.entity.IdBooking;
import com.example.jyxmyt.entity.InterRules;
import com.example.jyxmyt.entity.MeasurementofHeart;
import com.example.jyxmyt.entity.MedicalInfo;
import com.example.jyxmyt.entity.MenuPermissions;
import com.example.jyxmyt.entity.OrderReq;
import com.example.jyxmyt.entity.PeopleNumBooking;
import com.example.jyxmyt.entity.PeopleNumOrder;
import com.example.jyxmyt.entity.PsychologicalTest;
import com.example.jyxmyt.entity.Psychometrics;
import com.example.jyxmyt.entity.PsychometricsAnswer;
import com.example.jyxmyt.entity.PublicIpBunkInfo;
import com.example.jyxmyt.entity.PublicQueryUserDuty;
import com.example.jyxmyt.entity.QueryMsgInfo;
import com.example.jyxmyt.entity.QueryMsgPage;
import com.example.jyxmyt.entity.QueryMsgPageJson;
import com.example.jyxmyt.entity.Questionnaire;
import com.example.jyxmyt.entity.Right;
import com.example.jyxmyt.entity.RollCall;
import com.example.jyxmyt.entity.RoomInfo;
import com.example.jyxmyt.entity.RoomNumPlaceDuty;
import com.example.jyxmyt.entity.RoomRecord;
import com.example.jyxmyt.entity.ServcieAction;
import com.example.jyxmyt.entity.ServcieActionDetailed;
import com.example.jyxmyt.entity.ShopReq;
import com.example.jyxmyt.entity.Shopping;
import com.example.jyxmyt.entity.SynchFinger;
import com.example.jyxmyt.entity.SystemTime;
import com.example.jyxmyt.entity.WeekEdu;
import com.example.jyxmyt.entity.WeekRecipes;
import com.example.jyxmyt.entity.publicWeek;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tanghao on 2017/1/26.
 * 请求的API接口
 */
public interface CommonApi {
    //权力与义务
    @GET("kss/clientServcieAction!queryAllRightDuty.act")
    Observable<Entity<List<Right>>> getRight();

    //一日作息
    @GET("kss/clientServcieAction!queryAllDayWork.act")
    Observable<Entity<List<EveryDay>>> getDayRest(@Query("kssSeasonState") String kssSeasonState);

    //内部规定--监纪监规
    @GET("kss/clientServcieAction!queryAllInterRules.act")
    Observable<Entity<List<InterRules>>> getInterRules();

    //每周教育
    @GET("kss/clientServcieAction!queryAllWeekEdu.act")
    Observable<Entity<List<WeekEdu>>> getWeekEdu();

    //房间日记载
    @POST("kss/clientServcieAction!addDiaryInfo.act")
    Observable<Entity> getRoomRecord(@Body RoomRecord roomRecord);

    //每周食谱
    @POST("kss/clientServcieAction!queryWeekRecipes.act")
    Observable<Entity<List<WeekRecipes>>> getWeekRecipes();

    //市所版   鋪位安排以及轮班列表
    @POST("kss/clientServcieAction!queryByRoomNumPlaceDuty.act")
    Observable<Entity<List<RoomNumPlaceDuty>>> getRoomNumPlaceDuty(@Query("kssRoomNum") String kssRoomNum);

    //通过IP查询监区号、监室号
    @POST("kss/clientServcieAction!queryByIpRoomInfo.act")
    Observable<Entity<RoomInfo>> getRoomInfo(@Query("kssIpAdress") String kssIpAdress);

    //伙食标准
    @POST("kss/clientServcieAction!queryFoodStandards.act")
    Observable<Entity<FoodStandard>> getFoodStandard();

    //通过监室号查询人员指纹信息列表
    @POST("kss/clientServcieAction!queryPeopleFinger.act")
    Observable<Entity<List<Finger>>> getPeopleFinger(@Query("kssRoomNum") String kssRoomNum);

    //通过人员编号查询消费清单
    @POST("kss/clientServcieAction!queryByPeopleNumOrderList.act")
    Observable<Entity<PeopleNumOrder>> getPeopleNumOrder(@Query("kssPrisonerNum") String kssPrisonerNum, @Query("page") int page);

    //添加指纹信息
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("kss/clientServcieAction!addFinger.act")
    Observable<Entity> getAddFinger(@Body FingerReq fingerReq);

    //通过人员编号查询就医信息
    @POST("kss/clientServcieAction!queryMedical.act")
    Observable<Entity<MedicalInfo>> getMedicalInfo(@Query("kssPrisonerNum") String kssPrisonerNum, @Query("page") int page);

    //通过就医信息ID,批量修改指定数据状态、时间
    @POST("kss/clientServcieAction!updatesMedicalById.act")
    Observable<Entity> getMedicalById(@Query("idStr") String idStr);

    //添加点名信息，在点名设置时间内
    @POST("kss/clientServcieAction!addRollCall.act")
    Observable<Entity> getRollCall(@Body RollCall rollCall);

    //通过人员编号查询预约列表
    @POST("kss/clientServcieAction!queryByPeopleNumBooking.act")
    Observable<Entity<List<PeopleNumBooking>>> getPeopleNumBooking(@Query("kssPrisonerNum") String kssPrisonerNum);

    //通过预约ID查询预约信息
    @POST("kss/clientServcieAction!queryByIdBooking.act")
    Observable<Entity<IdBooking>> getIdBooking(@Query("id") int id);

    //添加预约信息，在指纹设置时间内
    @POST("kss/clientServcieAction!addBookingInfo.act")
    Observable<Entity> getBookingInfo(@Body BookingInfo bookingInfo);

    //查询全部接济单分类
    @POST("kss/clientServcieAction!queryAllHelpDetailed.act")
    Observable<Entity<List<ServcieActionDetailed>>> getServcieActionDetailed();

    //添加批量救济单
    @POST("kss/clientServcieAction!addHelps.act")
    Observable<Entity> getServcieAction(@Body List<ServcieAction> servcieAction);

    //同步指纹信息
    @POST("kss/clientServcieAction!synchFinger.act")
    Observable<Entity<List<SynchFinger>>> synchFingers(@Query("kssRoomNum") String kssRoomNum);

    //握手接口
//    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("kss/clientServcieAction!handshake.act")
    Observable<Entity> handshake(@Body HashMap hashMap);

    //初始化同步接口
    @POST("kss/clientServcieAction!initSynchFinger.act")
    Observable<Entity<List<SynchFinger>>> initFingers(@Query("kssRoomNum") String kssRoomNum);

    //通用版  通过监室号查询铺位
    @POST("kss/clientServcieAction!queryByIpBunkInfo.act")
    Observable<Entity<PublicIpBunkInfo>> getPublicIpBunkInfo(@Query("kssRoomNum") String kssRoomNum);

    //通用版  通过房间号查询七天值班值日
    @POST("kss/clientServcieAction!queryUserDuty.act")
    Observable<Entity<List<PublicQueryUserDuty>>> getPublicQueryUserDuty(@Body publicWeek WeekTj);

    @POST("kss/clientServcieAction!queryCommodity.act")
    Observable<Entity<Shopping>> getShops(@Body() ShopReq shopReq);

    @POST("kss/clientServcieAction!submitStatements.act")
    Observable<Entity> submitOrders(@Body() OrderReq orderReq);

    //查询心理测试题
    @POST("kss/clientServcieAction!queryPsychometrics.act")
    Observable<Entity<List<Psychometrics>>> getPsychometrics();

    //提交心理测评答案
    @POST("kss/clientServcieAction!submitPsychometricsAnswer.act")
    Observable<Entity> getSubmitPsychometricsAnswer(@Body PsychometricsAnswer psychometricsAnswer);

    //查询问卷调查测试题
    @POST("kss/clientServcieAction!queryQuestionnaire.act")
    Observable<Entity<List<Questionnaire>>> getQuestionnaire();

    //提交问卷调查答案
    @POST("kss/clientServcieAction!submitQuestionnaireAnswer.act")
    Observable<Entity> getSubmitQuestionnaireAnswer(@Body PsychometricsAnswer psychometricsAnswer);

    //检查当前时间，是否在大帐设置时间范围内
    @POST("kss/clientServcieAction!checkShoppingTime.act")
    Observable<Entity> checkShopTime();

    //添加点名信息，在点名设置时间内
    @POST("kss/clientServcieAction!checkRollCallTime.act")
    Observable<Entity> checkDMTime();

    //同步服务器时间
    @POST("kss/clientServcieAction!systemTime.act")
    Observable<Entity<SystemTime>> getSystemTime();

    //检查当前时间，是否在指纹设置时间范围内
    @POST("kss/clientServcieAction!checkFingerTime.act")
    Observable<Entity> checkFingerTime();

    //通过房间号查询留言信息
    @POST("kss/clientServcieAction!queryMsgInfo.act")
    Observable<Entity<List<QueryMsgInfo>>> queryMsgInfo(@Query("kssRoomNum") String kssRoomNum);

    //客户端留言获取成功通知服务器
    @POST("kss/clientServcieAction!updateMsgState.act")
    Observable<Entity> updateMsgState(@Query("msgIds") String msgIds);

    //查询留言分页信息
    @POST("kss/clientServcieAction!queryMsgPage.act")
    Observable<Entity<QueryMsgPageJson>> queryMsgPage(@Body QueryMsgPage queryMsgPage);

    //查询心理试卷
    @POST("kss/clientServcieAction!queryPsychologyPaper.act")
    Observable<Entity<List<PsychologicalTest>>> queryPsychologyPaper();

    //查询问卷调查试卷
    @POST("kss/clientServcieAction!queryQuestionnairePaper.act")
    Observable<Entity<List<PsychologicalTest>>> queryQuestionnairePaper();


    //通过试卷编号查询心理测评
    @POST("kss/clientServcieAction!queryByPaperNumP.act")
    Observable<Entity<List<MeasurementofHeart>>> queryByPaperNumP(@Query("paperNum") String paperNum);

    //通过试卷编号查询问卷调查
    @POST("kss/clientServcieAction!queryByPaperNumQ.act")
    Observable<Entity<List<MeasurementofHeart>>> queryByPaperNumQ(@Query("paperNum") String paperNum);

    //通过人员编号查询菜单权限
    @POST("kss/clientServcieAction!queryByPeopleNum.act")
    Observable<Entity<MenuPermissions>> queryByPeopleNum(@Query("kssPrisonerNum") String kssPrisonerNum);
}
