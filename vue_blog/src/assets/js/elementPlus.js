import { ElButton, ElAlert, ElForm, ElInput, ElFormItem } from 'element-plus'
export default(app)=>{
    app.use(ElButton)
    app.use(ElAlert)
    app.use(ElForm)
    app.use(ElFormItem)
    app.use(ElInput)
}