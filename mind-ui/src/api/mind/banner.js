import request from '@/utils/request'

// 查询幻灯片列表
export function listBanner(query) {
  return request({
    url: '/mind/banner/list',
    method: 'get',
    params: query
  })
}

// 查询幻灯片详细
export function getBanner(id) {
  return request({
    url: '/mind/banner/' + id,
    method: 'get'
  })
}

// 新增幻灯片
export function addBanner(data) {
  return request({
    url: '/mind/banner',
    method: 'post',
    data: data
  })
}

// 修改幻灯片
export function updateBanner(data) {
  return request({
    url: '/mind/banner',
    method: 'put',
    data: data
  })
}

// 删除幻灯片
export function delBanner(id) {
  return request({
    url: '/mind/banner/' + id,
    method: 'delete'
  })
}
