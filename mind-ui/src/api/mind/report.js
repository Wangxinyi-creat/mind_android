import request from '@/utils/request'

// 查询测试报告列表
export function listReport(query) {
  return request({
    url: '/mind/report/list',
    method: 'get',
    params: query
  })
}

// 查询测试报告详细
export function getReport(reportId) {
  return request({
    url: '/mind/report/' + reportId,
    method: 'get'
  })
}

// 新增测试报告
export function addReport(data) {
  return request({
    url: '/mind/report',
    method: 'post',
    data: data
  })
}

// 修改测试报告
export function updateReport(data) {
  return request({
    url: '/mind/report',
    method: 'put',
    data: data
  })
}

// 删除测试报告
export function delReport(reportId) {
  return request({
    url: '/mind/report/' + reportId,
    method: 'delete'
  })
}
