package com.yiyibaphoto.photo.bean;

/**
 * Created by SuHongJin on 2018/12/17.
 */

public class Bing {
    /**
     * showapi_res_error :
     * showapi_res_id : 3502b1255e1347a78c702dc042beb657
     * showapi_res_code : 0
     * showapi_res_body : {"ret_code":0,"data":{"title":"流光山色","date":"20181217","description":"奥索尤斯位于加拿大BC省的奥肯那根山谷，南部与美国华盛顿州接壤。对游客而言，这里的一年四季都充满吸引力。除了风景优美的山谷，这里还有各种文化活动和冒险项目，附近的奥索尤斯湖（Osoyoos Lake）是游泳、帆船、帆板和滑水等运动的中心。 Baldy Mountain Resort度假酒店位于山丘上，海拔1726米，是冬季雪鞋行走、雪橇、越野滑雪和高山滑雪的理想场所，奥索尤斯的夏季适合骑自行车、远足和观赏野生动物。现在，让我们坐下来欣赏远处风景如画的山丘吧。","img_1366":"http://api.mmno.com/api/bing/img_1366","copyright":"长曝光摄影下的奥索尤斯小镇车流 (© Bun Lee/Media Drum World/Aurora Photos)","img_1920":"http://api.mmno.com/api/bing/img_1920","subtitle":"万花筒般的多面小镇"},"ret_message":"Success"}
     */

    private String showapi_res_error;
    private String showapi_res_id;
    private int showapi_res_code;
    private ShowapiResBodyBean showapi_res_body;

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_id() {
        return showapi_res_id;
    }

    public void setShowapi_res_id(String showapi_res_id) {
        this.showapi_res_id = showapi_res_id;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * data : {"title":"流光山色","date":"20181217","description":"奥索尤斯位于加拿大BC省的奥肯那根山谷，南部与美国华盛顿州接壤。对游客而言，这里的一年四季都充满吸引力。除了风景优美的山谷，这里还有各种文化活动和冒险项目，附近的奥索尤斯湖（Osoyoos Lake）是游泳、帆船、帆板和滑水等运动的中心。 Baldy Mountain Resort度假酒店位于山丘上，海拔1726米，是冬季雪鞋行走、雪橇、越野滑雪和高山滑雪的理想场所，奥索尤斯的夏季适合骑自行车、远足和观赏野生动物。现在，让我们坐下来欣赏远处风景如画的山丘吧。","img_1366":"http://api.mmno.com/api/bing/img_1366","copyright":"长曝光摄影下的奥索尤斯小镇车流 (© Bun Lee/Media Drum World/Aurora Photos)","img_1920":"http://api.mmno.com/api/bing/img_1920","subtitle":"万花筒般的多面小镇"}
         * ret_message : Success
         */

        private int ret_code;
        private DataBean data;
        private String ret_message;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getRet_message() {
            return ret_message;
        }

        public void setRet_message(String ret_message) {
            this.ret_message = ret_message;
        }

        public static class DataBean {
            /**
             * title : 流光山色
             * date : 20181217
             * description : 奥索尤斯位于加拿大BC省的奥肯那根山谷，南部与美国华盛顿州接壤。对游客而言，这里的一年四季都充满吸引力。除了风景优美的山谷，这里还有各种文化活动和冒险项目，附近的奥索尤斯湖（Osoyoos Lake）是游泳、帆船、帆板和滑水等运动的中心。 Baldy Mountain Resort度假酒店位于山丘上，海拔1726米，是冬季雪鞋行走、雪橇、越野滑雪和高山滑雪的理想场所，奥索尤斯的夏季适合骑自行车、远足和观赏野生动物。现在，让我们坐下来欣赏远处风景如画的山丘吧。
             * img_1366 : http://api.mmno.com/api/bing/img_1366
             * copyright : 长曝光摄影下的奥索尤斯小镇车流 (© Bun Lee/Media Drum World/Aurora Photos)
             * img_1920 : http://api.mmno.com/api/bing/img_1920
             * subtitle : 万花筒般的多面小镇
             */

            private String title;
            private String date;
            private String description;
            private String img_1366;
            private String copyright;
            private String img_1920;
            private String subtitle;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getImg_1366() {
                return img_1366;
            }

            public void setImg_1366(String img_1366) {
                this.img_1366 = img_1366;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getImg_1920() {
                return img_1920;
            }

            public void setImg_1920(String img_1920) {
                this.img_1920 = img_1920;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }
        }
    }
}
