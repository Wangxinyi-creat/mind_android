import request from '@/utils/request'

// 查询心理测评列表
export function listAssessment(query) {
  return request({
    url: '/mind/assessment/list',
    method: 'get',
    params: query
  })
}

// 查询心理测评详细
export function getAssessment(assessmentId) {
  return request({
    url: '/mind/assessment/' + assessmentId,
    method: 'get'
  })
}

// 新增心理测评
export function addAssessment(data) {
  return request({
    url: '/mind/assessment',
    method: 'post',
    data: data
  })
}

// 修改心理测评
export function updateAssessment(data) {
  return request({
    url: '/mind/assessment',
    method: 'put',
    data: data
  })
}

// 删除心理测评
export function delAssessment(assessmentId) {
  return request({
    url: '/mind/assessment/' + assessmentId,
    method: 'delete'
  })
}
