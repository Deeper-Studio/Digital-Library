<template>
  <div class="bg-grey" >
    <el-row :gutter="2">
      <el-col :span="3"><div class="grid-content bg-purple-light"></div></el-col>
      <el-col :span="18"><div class="grid-content bg-white">
        <el-row :gutter="2">
          <el-col :span="20":offset="2"><div class="grid-content bg-white">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" label-position="left">
              <el-form-item label="Title" prop="title">
                <el-input v-model="ruleForm.title"></el-input>
              </el-form-item>
              <el-form-item label="Keywords" prop="keyword">
                <el-input v-model="ruleForm.keyword"></el-input>
              </el-form-item>
              <el-form-item label="Abstract" prop="abstract">
                <el-input type="textarea" v-model="ruleForm.abstract"></el-input>
              </el-form-item>
              <el-form-item label=" Add File ">
<!--                <el-upload-->
<!--                    class="upload-demo"-->
<!--                    ref="upload"-->
<!--                    action="https://jsonplaceholder.typicode.com/posts/"-->
<!--                    :on-preview="handlePreview"-->
<!--                    :on-remove="handleRemove"-->
<!--                    :file-list="fileList"-->
<!--                    :auto-upload="false">-->
<!--                  <el-button slot="trigger" size="small" type="primary">Add File</el-button>-->
<!--                  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload"> Upload </el-button>-->
<!--                  <div slot="tip" class="el-upload__tip">Only PDF file required</div>-->
<!--                </el-upload>-->
<!--                文件上传-->
                <el-upload
                    class="upload-demo"
                    drag
                    action="http://localhost:8088/"
                    multiple="false"
                    accept=".pdf, .PDF"
                    :http-request="requestFile"
                    :auto-upload="false"
                    :on-change="handlePreview"
                    file-list="filelist"

                    >
                  <i class="el-icon-upload"></i>
                  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                  <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
              </el-form-item>
              <el-row :gutter="2">
                <el-col :span="11">
                  <el-form-item label="Name" prop="name" required>
                    <el-input clearable v-model="ruleForm.name" placeholder="placeholder"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="2">  </el-col>
                <el-col :span="11">
                  <el-form-item clearable label="School" prop="school" required="">
                    <el-input v-model="ruleForm.school" placeholder="placeholder"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="2">
                <el-col :span="11">
                  <el-form-item clearable label="Email" prop="email" required>
                    <el-input v-model="ruleForm.email" placeholder="placeholder"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="2">  </el-col>
                <el-col :span="11">
                  <el-form-item clearable label="Address" prop="address" required="">
                    <el-input v-model="ruleForm.address" placeholder="placeholder"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">Create</el-button>
                <el-button @click="resetForm('ruleForm')">Reset</el-button>
              </el-form-item>
            </el-form>
          </div></el-col>
        </el-row>


      </div></el-col>
      <el-col :span="3"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Upload",
  data() {
    return {
      ruleForm: {
        title: '',
        keyword: '',
        abstract: '',
        name: '',
        school:'',
        email:'',
        address:'',
        filelist:[],
      },
      rules: {
        title: [
          { required: true, message: '请输入论文标题', trigger: 'blur' },
          { min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur' }
        ],
        keyword: [
          { required: true, message: '请输入关键字', trigger: 'blur' },
          { min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur' }
        ],
        abstract: [
          { required: true, message: '请输入摘要', trigger: 'blur' },
          { min: 2, trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur' }
        ],
        school: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm() {
      this.$message('Create Successfully!')
    },
    // submitForm(formName) {
    //   this.$refs[formName].validate((valid) => {
    //     if (valid) {
    //       alert('submit!');
    //     } else {
    //       console.log('error submit!!');
    //       return false;
    //     }
    //   });
    // },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handlePreview(file) {
      // console.log(file)
      this.temp.pdf_file = file.raw
      console.log(this.temp)
    },
    requestFile(param) {
      var fileObj = param.file
      console.log(fileObj)
      this.file = fileObj
      var FileController = this.uploadUrl    // 接收上传文件的后台地址
      var form = new FormData()    // FormData 对象
      form.append('pdf_file', fileObj)    // 文件对象
      // form.append('xxx', 'xxx')    // 其他参数
      var xhr = new XMLHttpRequest()    // XMLHttpRequest 对象
      xhr.open('post', FileController, true)
      xhr.send(form)
      console.log(this.fileList)
    },
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
  /*&:last-child {*/
  /*   margin-bottom: 0;*/
  /* }*/
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-grey{
  background-color: rgb(245,245,245);
}
.bg-white {
  background: white;
}
.bg-purple-light {
  background: rgb(245,245,245);
}
.grid-content {
  border-radius: 4px;
  min-height: 800px;
  width: 100%;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
