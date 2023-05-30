import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss'],
})

export class GalleryComponent implements OnInit {
  allImages: any[] = [];
  searchQuery: string = '';
  filteredGalleryItems: any[] = [];
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
    this.allImages = [
      {
        id:1,
        url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
        tags: [
          { name: 'cat', confidence: 0.9 },
          { name: 'animal', confidence: 0.8 },
          { name: 'cute', confidence: 0.7 },
        ],
        analyzedOn: Date.now(),
      },
      {
        id:2,
        url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
        tags: [
          { name: 'animal', confidence: 0.8 },

        ],
        analyzedOn: Date.now(),
      },
      {
        id:3,
        url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
        tags: [
          { name: 'cat', confidence: 0.9 },

        ],
        analyzedOn: Date.now(),
      },
      {
        id:4,
        url: 'https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*',
        tags: [
          { name: 'cute', confidence: 0.7 },
        ],
        analyzedOn: Date.now(),
      },
    ];
    this.route.queryParams.subscribe(params => {
      this.searchQuery = params['tags'] || '';
      this.filterGalleryItems();
    });

  }
  onClick(image: any) {
    this.router.navigate(['/image', image.id]);
  }
  getAllImages(imageUrl: string) {
    this.http
      .get('http://localhost:8080/images' + imageUrl)
      .subscribe((response) => {
        this.allImages.concat(response);
      });
  }

  onSearch() {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: { tags: this.searchQuery },
      queryParamsHandling: 'merge'
    });
    this.filterGalleryItems();
  }

  filterGalleryItems() {
    if (!this.searchQuery) {
      this.filteredGalleryItems = this.allImages;
    } else {
      const tags = this.searchQuery.split(',').map(tag => tag.trim().toLowerCase());
      this.filteredGalleryItems = this.allImages.filter(item => {
        return tags.every(tag => item.tags.some((itemTag:any) => itemTag.name.toLowerCase().includes(tag)));
      });
    }
  }
}
