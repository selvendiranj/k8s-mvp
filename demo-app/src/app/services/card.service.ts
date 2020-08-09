import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ResourceUrls } from '../utils/resource.urls';

@Injectable({
  providedIn: 'root'
})
export class CardService {

	constructor(public httpClient: HttpClient) { }

	getAllCards() {
		 return this.httpClient.get(ResourceUrls.getCardsUrl, ResourceUrls.getHttpOptions);
	 }

}