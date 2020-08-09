import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ResourceUrls } from '../utils/resource.urls';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

	constructor(public httpClient: HttpClient) { }

	getAllTransactions() {
		 return this.httpClient.get(ResourceUrls.getTransactionsUrl, ResourceUrls.getHttpOptions);
	 }

}