class PasteCode {
    constructor(props) {
        if (props == null) {
            return;
        }

        this.id = props.id;
        this.createTime = props.createTime;
        this.modifyTime = props.modifyTime;
        this.eid = props.eid;
        this.userId = props.userId;
        this.style = props.style;
        this.language = props.language;
        this.code = props.code;
    }
}