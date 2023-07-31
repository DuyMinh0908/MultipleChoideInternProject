interface ResponseMeta {
  message: string;
}
interface PaginationData<T> {
  current_page: number;
  data: Array<T>;
  first_page_url: string;
  from: number;
  last_page: number;
  last_page_url: string;
  next_page_url: string;
  path: string;
  per_page: number;
  prev_page_url: string;
  to: number;
  total: number;
}
export interface PaginationResponse<T> {
  meta: ResponseMeta;
  data: PaginationData<T>;
}
interface AllResponse<T> {
  meta: ResponseMeta;
  data: Array<T>;
}
interface UpdateResponse<T> {
  meta: ResponseMeta;
  data: T;
}
interface DefaultResponse<T> {
  meta: ResponseMeta;
  data: T;
}
