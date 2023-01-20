<template>
  <div  class="img"
      style="background-color: #ffe4f0;
                margin: 7px;
                border-radius: 10px;
                border-width: 10px;
                border-color: #2c3e50;
                ">
    <div style="height: 5px"></div>
    <div style="display: flex; width: 200px">
    <img :src="src"
         style="max-width: 70px;
         min-width: 20px;
         max-height: 300px;
         border-radius: 10px;
         margin-left: 5px"
    >
      <div style="color: #3e9f47;margin-left: 10px">
      {{name}}
      </div>
    </div>
    <div style="height: 5px"></div>
  </div>
</template>

<script>
export default {
  mounted() {
    },
  data(){
    return{
      whichSrc:"",
    showDialog:false,
    }
  },
  methods:{
  },
  components:{
  },
  name: "HisItem",
  props:{
    src: {
      type: String,
      default: ''
    },
    name: {
      type: String,
      default: ''
    },
  },

}
</script>

<style scoped>
.img{ cursor: pointer;}
</style>