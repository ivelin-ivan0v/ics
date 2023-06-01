import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss'],
})
export class GalleryComponent implements OnInit {
  imageId: string = '';
  allImages: any[] = [];
  constructor(private http: HttpClient, private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.imageId = params['id'];
      console.log(this.imageId);
      // You can use the ID parameter in your component logic here
    });
    this.allImages = [
      {
        url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
        tags: [
          { name: 'cat', confidence: 0.9 },
          { name: 'animal', confidence: 0.8 },
          { name: 'cute', confidence: 0.7 },
        ],
        analyzedOn: Date.now(),
      },
      {
        url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
        tags: [
          { name: 'cat', confidence: 0.9 },
          { name: 'animal', confidence: 0.8 },
          { name: 'cute', confidence: 0.7 },
        ],
        analyzedOn: Date.now(),
      },
      {
        url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
        tags: [
          { name: 'cat', confidence: 0.9 },
          { name: 'animal', confidence: 0.8 },
          { name: 'cute', confidence: 0.7 },
        ],
        analyzedOn: Date.now(),
      },
    ];
  }

  getAllImages(imageUrl: string) {
    this.http
      .get('http://localhost:8080/images' + imageUrl)
      .subscribe((response) => {
        this.allImages.concat(response);
      });
  }
}
