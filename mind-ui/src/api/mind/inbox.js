import request from '@/utils/request'

// 查询心晴信箱列表
export function listInbox(query) {
  return request({
    url: '/mind/inbox/list',
    method: 'get',
    params: query
  })
}

// 查询心晴信箱详细
export function getInbox(messageId) {
  return request({
    url: '/mind/inbox/' + messageId,
    method: 'get'
  })
}

// 新增心晴信箱
export function addInbox(data) {
  return request({
    url: '/mind/inbox',
    method: 'post',
    data: data
  })
}

// 修改心晴信箱
export function updateInbox(data) {
  return request({
    url: '/mind/inbox',
    method: 'put',
    data: data
  })
}

// 删除心晴信箱
export function delInbox(messageId) {
  return request({
    url: '/mind/inbox/' + messageId,
    method: 'delete'
  })
}
