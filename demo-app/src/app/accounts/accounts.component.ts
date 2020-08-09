import { Component, OnInit } from '@angular/core';

import { AccountService } from '../services/account.service';
import { Account } from '../models/account';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

	accounts:any=[];

	constructor(private accountService: AccountService) { }

	  ngOnInit(): void {
		  this.getAllAccounts();
	  }

	getAllAccounts() {
	    this.accountService.getAllAccounts().subscribe(
	      accounts => {
	        this.accounts = accounts;
	        console.log('Get Accounts : ' + accounts);
	      });
	  }
}
