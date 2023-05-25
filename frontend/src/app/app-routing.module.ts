import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddImageComponent} from "./add-image/add-image.component";


const routes: Routes = [
  { path: '', redirectTo: '/add-image', pathMatch: 'full' },
  {
    path: 'add-image', component: AddImageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
