<div class="relative mx-2">
  <span class="text-lg font-semibold">Locations</span>
  <button class="collapse_button absolute top-0 right-0 transition-transform duration-400 transform hover:text-gray-dark">
    @svg('heroicon-s-chevron-up', 'h-7 w-7')
  </button>
  <div class="relative overflow-hidden transition-max-h ease-out duration-300">
    <ul id="locations-info">
      @foreach($locations as $location)
        <li class="location-info">
          <div hidden class="uri">{{ $location["uri"] }}</div>
          <div hidden class="latitude">{{ $location["latitude"] }}</div>
          <div hidden class="longitude">{{ $location["longitude"] }}</div>
          <div hidden class="name">{{ $location["name"] }}</div>
          <div hidden class="population">{{ $location["population"] }}</div>
        </li>
        @endforeach
    </ul>

    <div class="my-8 text-lg text-gray italic text-center" id="loading-map-div">
      Loading map...
    </div>
    <div hidden id="locations-map" style="height: 400px" class="mt-5"></div>

  </div>
</div>
