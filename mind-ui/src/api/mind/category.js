import request from '@/utils/request'

// 查询分类列表
export function listCategory(query) {
  return request({
    url: '/mind/category/list',
    method: 'get',
    params: query
  })
}

// 查询分类详细
export function getCategory(id) {
  return request({
    url: '/mind/category/' + id,
    method: 'get'
  })
}

// 新增分类
export function addCategory(data) {
  return request({
    url: '/mind/category',
    method: 'post',
    data: data
  })
}

// 修改分类
export function updateCategory(data) {
  return request({
    url: '/mind/category',
    method: 'put',
    data: data
  })
}

// 删除分类
export function delCategory(id) {
  return request({
    url: '/mind/category/' + id,
    method: 'delete'
  })
}
