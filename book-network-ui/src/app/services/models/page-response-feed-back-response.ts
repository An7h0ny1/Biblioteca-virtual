/* tslint:disable */
/* eslint-disable */
import { FeedBackResponse } from '../models/feed-back-response';
export interface PageResponseFeedBackResponse {
  content?: Array<FeedBackResponse>;
  first?: boolean;
  last?: boolean;
  pageNumber?: number;
  pageSize?: number;
  totalElements?: number;
  totalPages?: number;
}
