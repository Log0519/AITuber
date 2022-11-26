<script setup>

</script>
<template>

  <div style="padding:10px">
    <div style="margin: 10px 10px;display: flex;font-size: 15px;color: #3a9113">
      地点 :<div style="display: flex;margin-left: 10px;width: 10%">
      <el-select v-model="pace" placeholder="选择地点">
        <el-option
            v-for="item in optionsPace"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

    </div>
      <div style="margin-left: 20px"></div>
      <div style="display: flex">
        <div class="block" style="padding-left: 50px">
          <span class="demonstration">起始时间 ：</span>
          <el-date-picker

              v-model="timeBegin"
              type="date"
              placeholder="选择日期"
              value-format="YYYY-MM-DD"
          >
          </el-date-picker>
        </div>
        <div style="margin-left: 10px"></div>
        <div class="block">
          <span class="demonstration">截止时间 ：</span>
          <el-date-picker
              v-model="timeEnd"
              type="date"
              placeholder="选择日期"
              value-format="YYYY-MM-DD"
          >
          </el-date-picker>
          <el-button type="primary" @click="load">查询</el-button>
        </div>
      </div>
    </div>


    <div style="margin: 10px 0">

    </div>
    <el-table
        :data="tableData"
        stripe="true"
        border="true"
        style="color: #39a15d"
    >

      <el-table-column
          prop="pace"
          label="地点"
      >
      </el-table-column>
      <el-table-column
          prop="time"
          label="时间"
      >
      </el-table-column>
      <el-table-column
          :prop="so2"
          :label="so2">
      </el-table-column>
      <el-table-column
          :prop="no2"
          :label="no2">
      </el-table-column>
      <el-table-column
          :prop="pm10"
          :label="pm10">
      </el-table-column>
      <el-table-column
          :prop="co"
          :label="co">
      </el-table-column>
      <el-table-column
          :prop="o3"
          :label="o3">
      </el-table-column>
      <el-table-column
          :prop="pm25"
          :label="pm25">
      </el-table-column>
      <el-table-column
          :prop="point"
          :label="point">
      </el-table-column>


    </el-table>

    <div style="margin: 5px;padding-left: 400px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          v-model:current-page="currentPage"
          :page-sizes="[5, 10, 15]"
          v-model:page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          background="true"
          :total="total"
          small>
      </el-pagination>
    </div>
  </div>

</template>
