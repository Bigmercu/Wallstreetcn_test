package com.example.bigmercu.wallstreetcn_test.entity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message{
    /**
     * Id : 3066
     * AuthorId : 10008
     * Style : 2
     * Title : 黄金期货投机性净多头头寸创历史新高，黄金股或迎来交易机会，龙头就买这几只
     * Summary : 今日重要性：✨✨
     * Image :
     * ImageType :
     * Url : https://bao.wallstreetcn.com/web/articles/3066
     * Source : 选股宝
     * DisplayAuthor :
     * Liked : false
     * LikeCount : 5
     * CreatedAt : 1462753336
     * UpdatedAt : 1462760625
     * Type : 0
     * ShareUrl : https://bao.wallstreetcn.com/web/messages/3066
     * Stocks : [{"Name":"湖南黄金","Symbol":"002155.SZ"},{"Name":"山东黄金","Symbol":"600547.SS"},{"Name":"赤峰黄金","Symbol":"600988.SS"}]
     */

    private List<MessagesBean> Messages;

    public List<MessagesBean> getMessages() {
        return Messages;
    }

    public void setMessages(List<MessagesBean> Messages) {
        this.Messages = Messages;
    }

    public static class MessagesBean {
        @SerializedName("Id")
        private String Id;
        @SerializedName("AuthorId")
        private String AuthorId;
        @SerializedName("Style")
        private int Style;
        @SerializedName("Title")
        private String Title;
        @SerializedName("Summary")
        private String Summary;
        @SerializedName("Image")
        private String Image;
        @SerializedName("ImageType")
        private String ImageType;
        @SerializedName("Url")
        private String Url;
        @SerializedName("Source")
        private String Source;
        @SerializedName("DisplayAuthor")
        private String DisplayAuthor;
        @SerializedName("Liked")
        private boolean Liked;
        @SerializedName("LikeCount")
        private String LikeCount;
        @SerializedName("CreatedAt")
        private int CreatedAt;
        @SerializedName("UpdatedAt")
        private int UpdatedAt;
        @SerializedName("Type")
        private int Type;
        @SerializedName("ShareUrl")
        private String ShareUrl;
        /**
         * Name : 湖南黄金
         * Symbol : 002155.SZ
         */

        private List<StocksBean> Stocks;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getAuthorId() {
            return AuthorId;
        }

        public void setAuthorId(String AuthorId) {
            this.AuthorId = AuthorId;
        }

        public int getStyle() {
            return Style;
        }

        public void setStyle(int Style) {
            this.Style = Style;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getSummary() {
            return Summary;
        }

        public void setSummary(String Summary) {
            this.Summary = Summary;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getImageType() {
            return ImageType;
        }

        public void setImageType(String ImageType) {
            this.ImageType = ImageType;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }

        public String getDisplayAuthor() {
            return DisplayAuthor;
        }

        public void setDisplayAuthor(String DisplayAuthor) {
            this.DisplayAuthor = DisplayAuthor;
        }

        public boolean isLiked() {
            return Liked;
        }

        public void setLiked(boolean Liked) {
            this.Liked = Liked;
        }

        public String getLikeCount() {
            return LikeCount;
        }

        public void setLikeCount(String LikeCount) {
            this.LikeCount = LikeCount;
        }

        public int getCreatedAt() {
            return CreatedAt;
        }

        public void setCreatedAt(int CreatedAt) {
            this.CreatedAt = CreatedAt;
        }

        public int getUpdatedAt() {
            return UpdatedAt;
        }

        public void setUpdatedAt(int UpdatedAt) {
            this.UpdatedAt = UpdatedAt;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getShareUrl() {
            return ShareUrl;
        }

        public void setShareUrl(String ShareUrl) {
            this.ShareUrl = ShareUrl;
        }

        public List<StocksBean> getStocks() {
            return Stocks;
        }

        public void setStocks(List<StocksBean> Stocks) {
            this.Stocks = Stocks;
        }

        public static class StocksBean {
            public static final String TRADE = "TRADE";//正常交易
            public static final String HALT = "HALT";//停牌
            public static final String BREAK = "BREAK";//休市
            public static final String ENDTR = "ENDTR";//收盘
            public static final String OCALL = "OCALL";//集合竞价(09:15 --09:30)

            @SerializedName("id")
            public String id;
            @SerializedName("Name")
            private String Name;
            @SerializedName("Symbol")
            private String Symbol;
            @SerializedName("isFav")
            private String isFav;//是否收藏
            @SerializedName("px_change")
            private String px_change;//涨跌额
            @SerializedName("")
            private String last_px;//最新价格 现价
            @SerializedName("last_px")
            private String px_change_rate;//涨跌幅
            @SerializedName("px_change_rate")
            private String trade_status; //交易状态 "TRADE"=>正常交易  "HALT"=>停牌

            public String getIsFav() {
                return isFav;
            }

            public void setIsFav(String isFav) {
                this.isFav = isFav;
            }

            public String getLast_px() {
                return last_px;
            }

            public void setLast_px(String last_px) {
                this.last_px = last_px;
            }

            public String getPx_change() {
                return px_change;
            }

            public void setPx_change(String px_change) {
                this.px_change = px_change;
            }

            public String getPx_change_rate() {
                return px_change_rate;
            }

            public void setPx_change_rate(String px_change_rate) {
                this.px_change_rate = px_change_rate;
            }

            public String getTrade_status() {
                return trade_status;
            }

            public void setTrade_status(String trade_status) {
                this.trade_status = trade_status;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @SerializedName("trade_status")


            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getSymbol() {
                return Symbol;
            }

            public void setSymbol(String Symbol) {
                this.Symbol = Symbol;
            }
        }

        @Override
        public String toString() {
            return "MessagesBean{" +
                    "Id='" + Id + '\'' +
                    ", AuthorId='" + AuthorId + '\'' +
                    ", Style=" + Style +
                    ", Title='" + Title + '\'' +
                    ", Summary='" + Summary + '\'' +
                    ", Image='" + Image + '\'' +
                    ", ImageType='" + ImageType + '\'' +
                    ", Url='" + Url + '\'' +
                    ", Source='" + Source + '\'' +
                    ", DisplayAuthor='" + DisplayAuthor + '\'' +
                    ", Liked=" + Liked +
                    ", LikeCount=" + LikeCount +
                    ", CreatedAt=" + CreatedAt +
                    ", UpdatedAt=" + UpdatedAt +
                    ", Type=" + Type +
                    ", ShareUrl='" + ShareUrl + '\'' +
                    ", Stocks=" + Stocks +
                    '}';
        }
    }
}
