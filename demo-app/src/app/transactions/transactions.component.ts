import { Component, OnInit } from '@angular/core';

import { TransactionService } from '../services/transaction.service';
import { Transaction } from '../models/transaction';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  transactions : any = [];

  constructor(private transactionService: TransactionService) { 
  }

  ngOnInit(): void {
  	this.getAllTransactions();
  }
  
  getAllTransactions() {
    this.transactionService.getAllTransactions().subscribe(
      transactions => {
        this.transactions = transactions;
        console.log('Get All Transactions: ' + transactions);
      });
  }

}
