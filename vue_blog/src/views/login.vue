<template>
  <div class="container animated bounceInLeft">
    <div class="box">
      <div class="boxChi">
        <el-form label-width="80px" ref="formRef" :rules="rules" :model="formLabelAlign">
          <el-form-item label="账号" prop="username">
            <el-input v-model="formLabelAlign.username"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码">
            <el-input v-model="formLabelAlign.password"></el-input>
          </el-form-item>
          <el-form-item prop="code" label="验证码">
            <el-input class="codeInput" v-model="formLabelAlign.code"></el-input>
            <span @click="getCaptchaCode" class="codeSpan" v-html="code"></span>
          </el-form-item>
        </el-form>
        <el-button type="success" @click="loginFun">登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
//import { userRouter , useRole } from "vue-router";
import axios from "/src/assets/js/axios";
import{ ElMessage } from "element-plus";
import{ reactive, ref, onMounted} from "vue";

export default {
  setup(props){
    //let router = useRouter();
    const validateUsername = (rule,value,callback) => {
      if(value === ""){
        callback(new Error("请输入姓名"));
      }else {
        callback();
      }
    };
    const validatePassword = (rule,value,callback) => {
      if(value === ""){
        callback(new Error("请输入密码"));
      }else {
        callback();
      }
    };
    const validateCode = (rule,value,callback) => {
      if(value === ""){
        callback(new Error("请输入验证码"));
      }else {
        callback();
      }
    };
    const formLabelAlign = reactive({
      username : "",
      password : "",
      code : ""
    });
    let formRef = ref(null);
    let code = ref(null);

    onMounted(()=>{
      getCaptchaCode();
    });

    const getCaptchaCode = () => {
      axios.get("/captcha").then(res => {
        console.log(res);
        // res.code === "200" && (code.value = res.data.code);
        // ElMessage.success({
        //   message: "验证码获取成功",
        //   type: "success"
        // });
        //localStorage.setItem("token", res.data.token);
      });
    };
    const loginFun= () => {
      formRef.value.validate(valid =>{
        if(valid) {
          axios.post("/login", formLabelAlign).then(res => {
            console.log(res);
            //res.code === "200" && localStorage.setItem("token", res.data.token);
            // ElMessage.success({
            //   message: res.data.data,
            //   type: "success"
            // });
            // if (res.data.code == 1) {
            //   localStorage.setItem("username", formLabelAlign.username);
            //   router.push("/");
            // }
          });
        }else {
          alert("请填写必填信息");
          return false;
        }
      });
    };
    const rules = {
      username: [{ validator:validateUsername,trigger: "blur"}],
      password: [{ validator: validatePassword, trigger: "blur"}],
      code:[{ validator:validateCode, trigger: "blur" }]
    };
    return {formLabelAlign,formRef,rules,loginFun,code,getCaptchaCode};
  }
}
</script>

<style lang="less" scoped>
.container{
  position: relative;
  width: 100%;
  height: 100%;
  background-repeat:no-repeat;
  background-size: 100%;
  .box{
    width:500px;
    height: 400px;
    background-color: rgba(0,0,0,0.2);
    position: absolute;
    right: 10%;
    top: 26%;

  }
}


</style>