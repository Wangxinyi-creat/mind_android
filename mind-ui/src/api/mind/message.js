import request from '@/utils/request'

// 查询消息列表
export function listMessage(query) {
  return request({
    url: '/mind/message/list',
    method: 'get',
    params: query
  })
}

export function listAllMessage(query) {
  return request({
    url: '/mind/message/listAll',
    method: 'get',
    params: query
  })
}

// 查询消息详细
export function getMessage(messageId) {
  return request({
    url: '/mind/message/' + messageId,
    method: 'get'
  })
}

export function getNextMessage(messageId, recordId) {
  return request({
    url: '/mind/message/next/' + recordId + '/' + messageId,
    method: 'get'
  })
}

// 新增消息
export function addMessage(data) {
  return request({
    url: '/mind/message',
    method: 'post',
    data: data
  })
}

// 修改消息
export function updateMessage(data) {
  return request({
    url: '/mind/message',
    method: 'put',
    data: data
  })
}

// 删除消息
export function delMessage(messageId) {
  return request({
    url: '/mind/message/' + messageId,
    method: 'delete'
  })
}
