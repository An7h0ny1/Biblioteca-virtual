import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookRoutingModule } from './book-routing.module';
import { MainComponent } from './pages/main/main.component';
import { BookListComponent } from './pages/book-list/book-list.component';
import { BookCardComponent } from "./components/book-card/book-card.component";
import { RatingComponent } from './components/rating/rating.component';
import { MenuComponent } from "./components/menu/menu.component";


@NgModule({
  declarations: [
    MainComponent,
    BookListComponent,
    MenuComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    BookCardComponent,
    RatingComponent
]
})
export class BookModule { }
