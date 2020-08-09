import { environment } from '../../environments/environment';
import { HttpHeaders } from '@angular/common/http';

const appUrl = environment.serviceUrl;
const accountsAppPort = environment.accountsServicePort;
const cardsAppPort = environment.cardsServicePort;
const transactionsAppPort = environment.transactionsServicePort;

const defaultHttpOptions = {
  headers: new HttpHeaders (
    {
      'Content-Type': 'application/json',
    }
  )
};

export const ResourceUrls = {
		
  getAccountsUrl: appUrl + accountsAppPort + '/accounts',
  getCardsUrl: appUrl + cardsAppPort + '/cards',
  getTransactionsUrl: appUrl + transactionsAppPort + '/transactions',
  getHttpOptions : defaultHttpOptions,
};
