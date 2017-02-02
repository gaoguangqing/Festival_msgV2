package com.spoon.ggq.festival_msg.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggq on 2017/1/29.
 */
public class FestivalLab {
    private  static  FestivalLab mInstance;

    public static  FestivalLab getInstance(){
        if (mInstance==null)
        {
            synchronized (FestivalLab.class)
            {
                if (mInstance==null)
                    mInstance=new FestivalLab();
            }
        }
        return mInstance;
    }

    private List<Festival> mFestivals=new ArrayList<Festival>();
    private List<Msg> mMsgs=new ArrayList<Msg>();
    private FestivalLab(){
        mFestivals.add(new Festival(1,"国庆节"));
        mFestivals.add(new Festival(2,"中秋节"));
        mFestivals.add(new Festival(3,"元旦"));
        mFestivals.add(new Festival(4,"春节"));
        mFestivals.add(new Festival(5,"七夕节"));
        mFestivals.add(new Festival(6,"端午节"));
        mFestivals.add(new Festival(7,"圣诞节"));
        mFestivals.add(new Festival(8,"儿童节"));

        mMsgs.add(new Msg(1,1,"一份开心开怀，一份快乐飞来，一份好运飘来，一份轻松吹来，一份关怀传来，一份祝福寄来，一份问候为你来，祝你长假七天乐常在，福运国庆笑开怀。"));
        mMsgs.add(new Msg(2,1,"金秋时节好气候，丹桂花落香气留，国内国外旅旅游，亲友相聚饮饮酒，开心快乐美悠悠，吉祥如意伴你走，朋友短信送问候，祝你国庆快乐，幸福永久！"));
        mMsgs.add(new Msg(3,1,"飞舞的彩带是我的关怀，喧天的锣鼓是我的祝福。国庆佳节到了，祝你全家红红火火，和和美美，开开心心！"));
        mMsgs.add(new Msg(4,1,"国庆到，喜庆到，秋分阵阵果飘香，国庆盈盈新婚闹，十一黄金周对对新人把婚结，十一黄金周全家出游乐融融，短信祝福忙也来送到，轻轻的问候国庆快乐！"));
        mMsgs.add(new Msg(5,1,"千万个思念，在空气中凝固。扬起风吹向你，带着我的祝福，寂寞我不在乎，你快乐我就满足，想你是我的幸福！国庆佳节，盼望与你相聚！"));
        mMsgs.add(new Msg(6,1,"国庆国庆普天同庆，重阳重阳万事不难，在这个和平幸福的年代里，祝你们全家节日快乐！身体健康！"));
        mMsgs.add(new Msg(7,1,"金秋十月，举国同庆；神州大地，繁花似锦；家和国盛，乐曲如潮，借着这伟大而美丽的日子送上我最诚挚的祝福：天天开心，事事顺意！"));
        mMsgs.add(new Msg(8,1,"一年一度的立冬，没有国庆的隆重，也没有中秋的激情，但却有远方朋友的我，不吝啬一毛钱的问候：天冷了，添衣了吗？"));
        mMsgs.add(new Msg(9,1,"国庆佳节六十三周年大庆，中秋佳节多少年的传统，双节相逢是多么喜庆，愿您的两个节日都过得开心，愿您的全家快乐团聚，愿您的欢乐笑在每一个日子！"));


        mMsgs.add(new Msg(1,2,"一个萝卜两片藕，幸福与你手牵手；三两豆腐四片姜，观物你宜放眼量；五个月饼六块糖，平等沟通团圆有。"));
        mMsgs.add(new Msg(2,2,"相思明月中，祝福花香里：一愿人团圆，千里共婵娟；二愿事业丰，万事皆如意；三愿爱美满，情意永相随。亲爱的客户，中秋快乐！"));
        mMsgs.add(new Msg(3,2,"又是一年落叶黄，一层秋雨一层凉。整日工作挺辛苦，天凉别忘加衣裳。保重身体多餐饭，珍惜友情常想想。信短情长言未尽，唯愿朋友多安康。中秋快乐，合家团圆！"));

        mMsgs.add(new Msg(1,3,"让元旦的欢乐驱走你生活的疲惫，让你的心灵跟随节日气氛一起陶醉；愿假期的休息带给你精力充沛，愿你节后上班信心百倍！祝你生活美丽、工作顺利！"));
        mMsgs.add(new Msg(2,3,"好人好梦路好走，牵手走过九十九，举杯喝了新年酒，幸福生活到永久，祝你好运时时有，财源滚滚抓到手，元旦别忘老朋友，短信互动常问候！"));
        mMsgs.add(new Msg(3,3,"欢天喜地迎元旦，吃好喝好别心烦，放松情绪心放宽，走出陋室游山川，风调雨顺心情好，忘掉烦忧身体健，关爱自己天高远，幸福吉祥保平安！"));

        mMsgs.add(new Msg(1,4,"春节祝您一帆风顺二龙腾飞三羊开泰四季平安五福临门六六大顺七星高照八方来财九九同心十全十美百事亨通千事吉祥万事如意！"));
        mMsgs.add(new Msg(2,4,"春节欢欢乐乐，烟花灿灿烂烂，爱情甜甜蜜蜜，幸福缠缠绵绵，生活红红火火，好运永永远远，祝福温温馨馨，愿我们的恩恩爱爱、团团圆圆、长长久久。"));
        mMsgs.add(new Msg(3,4,"海豚想给天使一个吻可惜天太高了，天使想给海豚一个吻可惜海太深了，我想给你一个拥抱可惜太远了，只有给你发一条短信轻轻的告诉你：新春快乐。"));

        mMsgs.add(new Msg(1,5,"这是一条王母娘娘赐予的短信，凡是读到它的人将终生幸福，恋爱中的情侣将一生相爱，单身男女将遇美满爱情，祝愿全天下人七夕快乐。"));
        mMsgs.add(new Msg(2,5,"相思苦，爱意浓，愿爱在你心中谱；银河美，鹊桥仙，愿爱在你身边绕；七夕到，喜鹊叫，让爱把你紧拥抱；情相依，爱无边，幸福伴你一生永不变！"));
        mMsgs.add(new Msg(3,5,"七月七日七夕夜，牛郎织女来会面。天上人间情难断，恩恩爱爱心不变。哪怕王母银河变，也有鹊桥助神仙。你我情比磐石坚，坎坷虽有难断绝。愿你七夕快乐笑开颜，你我相伴到海枯石烂！"));


        mMsgs.add(new Msg(1,6,"送你一个粽子，含量成分：100%纯关心。配料：甜蜜+快乐+开心+宽容+忠诚=幸福；保质期：一辈子。保存方法：珍惜。朋友，端午节快乐！"));
        mMsgs.add(new Msg(2,6,"一粒粒糯米，粘稠着我的思念；一颗颗红枣，点缀着日子的红艳；一片片粽叶，贴上了幸福的标签；一圈圈丝线，缠绕着美好心愿；一个个粽子，包裹着幸福万千；一口口品尝着，生活的香甜。祝你端午节快乐无限！"));
        mMsgs.add(new Msg(3,6,"送您一串香甜的粽子，让轻轻的叶子装着满满的问候，小小的菱角捻出丝丝的情意，长长的丝线系着柔柔的祝福，浓浓的清香露出片片真情，祝端午节快乐！"));

        mMsgs.add(new Msg(1,7,"吉祥的驯鹿在奔跑，平安的银铃在叮当；幸福的雪花在飞舞，快乐的雪橇在飞驰；慈祥的老人在微笑，圣诞的礼物在送达；开心的喜悦在蔓延，真挚的祝福在发送：圣诞快乐！"));
        mMsgs.add(new Msg(2,7,"装扮圣诞树，备好圣诞餐，愿你圣诞快乐平安，送出平安果，端出幸福茶，愿你圣诞乐哈哈，吉祥来相伴，祝福来问候，愿你圣诞更狂欢，幸福日子，温暖祝福，愿你岁岁有平安。"));
        mMsgs.add(new Msg(3,7,"美酒醇，火鸡香，吉祥伴着雪花飞；苹果红，炉火旺，鸿运随着烟花来；歌声飘，钟声荡，平安围着青松绕。圣诞节，在你的圣诞袜里，我装了一条祝福短信，愿你节日快乐，心想事成。"));

        mMsgs.add(new Msg(1,8,"六一，六一，最好做到六个一：让我们傻一点，嫩一点，笨一点，幼稚一点，可爱一点，开心一点。虽然大龄了，但是快乐无极限，儿童节，祝老小孩们开心！"));
        mMsgs.add(new Msg(2,8,"细数年轻的梦，轻拂幻想的风；依恋年少的雨，踏寻纯真的心；你我悄悄长大，童年依然美丽。一曲笛声也悠长，愿这载满幸福的音符，唱响你成长的歌！"));
        mMsgs.add(new Msg(3,8,"很多年前我给你发了一条短信，因为祝福太多太沉重，所以这么多年才刚寄到：六一儿童节快乐！愿你是全天下最幸福，最快乐，最聪明，最可爱，最美丽的小孩子！"));

    }
    public List<Festival> getFestivals(){
        return new ArrayList<Festival>(mFestivals);
    }
    public Festival getFestivalById(int fesId)
    {
        for (Festival festival:mFestivals
             ) {
            if (festival.getId()==fesId)
                return festival;
        }
        return null;
    }
    public List<Msg>getMsgsByFestivalId(int fesId){
        List<Msg> msgs=new ArrayList<>();
        for (Msg msg:mMsgs)
        {
            if (msg.getFestivalId()==fesId)
            {
                msgs.add(msg);
            }
        }
        return msgs;
    }
    public  Msg getMsgByMsgId(int id){
        for (Msg msg:mMsgs
             ) {
            if(msg.getId()==id)
                return msg;
        }
        return null;
    }



}
