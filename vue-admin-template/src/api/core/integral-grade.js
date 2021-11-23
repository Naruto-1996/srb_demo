import request from '@/utils/request'

export default {

  // 获取积分列表
  list(){
    return request({
      url: '/admin/core/integralGrade/list',
      method: 'get'
    })
  },
  removeById(id){
    return request({
      url: '/admin/core/integralGrade/removeById/' + id,
      method: 'delete'
    })
  },
  getById(id){
    return request({
      url: '/admin/core/integralGrade/getById/' + id,
      method: 'get'
    })
  },
  update(integralGrade){
    return request({
      url: '/admin/core/integralGrade/update',
      method: 'put',
      data: integralGrade
    })
  },
  addIntegralGrade(integralGrade){
    return request({
      url: '/admin/core/integralGrade/add',
      method: 'post',
      data: integralGrade  // 如果后端接口需要的是json字符串的话 axios 固定字段data会将对象转换为json字符串进行传输
    })
  }

}
