package com.wyy.start.coupon.weixin;

import java.util.List;

/**
 * All rights Reserved,Designed By www.freemud.cn
 *
 * @Title: TulingResult
 * @Package com.wyy.start.coupon.weixin;
 * @Description:
 * @Author: Administrator
 * @Date: 2019/5/12 22:09
 * @Version V1.0
 * @Copyright:2018www.freemud.cn Inc.All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
public class TulingResult {
    /**
     * emotion : {"robotEmotion":{"a":0,"d":0,"emotionId":0,"p":0},"userEmotion":{"a":0,"d":0,"emotionId":10300,"p":0}}
     * intent : {"actionName":"","code":10004,"intentName":""}
     * results : [{"groupType":1,"resultType":"text","values":{"text":"又见面啦！"}}]
     */

    private List<ResultsBean> results;


    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }


    public static class ResultsBean {
        /**
         * groupType : 1
         * resultType : text
         * values : {"text":"又见面啦！"}
         */

        private int groupType;
        private String resultType;
        private ValuesBean values;

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public ValuesBean getValues() {
            return values;
        }

        public void setValues(ValuesBean values) {
            this.values = values;
        }

        public static class ValuesBean {
            /**
             * text : 又见面啦！
             */

            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
