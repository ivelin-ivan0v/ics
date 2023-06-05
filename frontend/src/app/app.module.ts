import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddImageComponent } from './add-image/add-image.component';
import { HeaderComponent } from './core/header/header.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ImageComponent } from './image/image.component';
import { HttpClientModule } from '@angular/common/http';
import { GalleryComponent } from './gallery/gallery.component';
import { ThemeService } from './core/theme.service';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'primeng/tooltip';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AddImageComponent,
    ImageComponent,
    GalleryComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    BsDropdownModule.forRoot(),
    TypeaheadModule.forRoot(),
    TooltipModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [ThemeService],
  bootstrap: [AppComponent],
})
export class AppModule {}
