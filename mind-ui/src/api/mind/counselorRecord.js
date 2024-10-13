import request from '@/utils/request'

// 查询咨询师预约记录列表
export function listCounselorRecord(query) {
  return request({
    url: '/mind/counselorRecord/list',
    method: 'get',
    // params: query
  })
}

// 查询咨询师预约记录详细
export function getCounselorRecord(recordId) {
  return request({
    url: '/mind/counselorRecord/' + recordId,
    method: 'get'
  })
}

// 新增咨询师预约记录
export function addCounselorRecord(data) {
  return request({
    url: '/mind/counselorRecord',
    method: 'post',
    data: data
  })
}

// 修改咨询师预约记录
export function updateCounselorRecord(data) {
  return request({
    url: '/mind/counselorRecord',
    method: 'put',
    data: data
  })
}

// 删除咨询师预约记录
export function delCounselorRecord(recordId) {
  return request({
    url: '/mind/counselorRecord/' + recordId,
    method: 'delete'
  })
}
