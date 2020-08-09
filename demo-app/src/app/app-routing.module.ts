import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AccountsComponent } from './accounts/accounts.component';
import { CardsComponent } from './cards/cards.component';
import { TransactionsComponent } from './transactions/transactions.component';

const routes: Routes = [
  { path: 'accounts', component: AccountsComponent },
  { path: 'cards', component: CardsComponent },
  { path: 'transactions', component: TransactionsComponent },
  { path: '',   redirectTo: '/transactions', pathMatch: 'full' }, // redirect to `first-component`
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
