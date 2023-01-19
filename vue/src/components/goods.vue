<template>
  <div style="background-color: #1f7ecc;border: solid;border-color: #f2f2f2">
    <div style="margin-left: 4px;margin-right: 10px">
      <div style="height: 10px"></div>
    <div style="color: #f2f2f2;font-size: 12px">
      {{goodsName}}
    </div>
    <div style="color: #ffe2a9;font-size: 11px;text-align: right">
      {{goodsPrice}}
    </div>
      <div style="height: 10px"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "good",
  props:{
    goodsName: {
      type:String,
      default: ''
    },
    goodsPrice: {
      type:String,
      default: ''
    },
  },
  data(){
    return{

    }
  }
}

</script>

<style scoped>

</style>