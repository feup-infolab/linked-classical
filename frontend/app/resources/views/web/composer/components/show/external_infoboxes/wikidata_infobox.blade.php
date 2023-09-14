<div class="mx-2 relative mb-5 ">
  <span class="text-lg font-semibold">Wikidata Properties</span>
  <button class="collapse_button absolute top-0 right-0 justify-end transition-transform duration-400 transform">
    @svg('heroicon-s-chevron-up', 'h-7 w-7')
  </button>

  <div class="relative overflow-hidden transition-max-h ease-out duration-300">
    <div class="my-6 bg-gray-lightest rounded relative">
      <div class="px-8 py-4 mt-5">

        <div class="relative">
          <input id="propertySearch" class="px-1 py-2 w-full border-b text-sm bg-gray-lightest" type="text" placeholder="Search for property">
          <button class="absolute top-2 right-2 hover:text-gray-dark">
            @svg('heroicon-s-search', 'w-5 h-5')
          </button>
        </div>

        <div class="my-4 flex" style="max-height: 40vh; overflow: auto;">
          <!-- Table -->
          <div class="w-full bg-white">
            <table id="wikidata-table" class="table table-bordered table-striped rounded-lg text-sm">
              <tr>
                <th>Property</th>
                <th>Value</th>
              </tr>

              @forelse($info as $infoItem)

                @if($infoItem["propertyLabel"] === "coordinate location@en" || $infoItem["propertyLabel"] === "coordinate location")
                  <div hidden id="gps-coordinates">{{ $infoItem["value"] }}</div>
                @endif

                <tr>
                  <td class="property">
                    <a href="{{$infoItem["property"]}}" target="_blank" class="underline">
                      @if(array_key_exists("property", $infoItem))
                        {{ $infoItem["propertyLabel"] }}
                      @endif
                    </a>
                  </td>
                  <td style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; max-width: 50ch;">
                    @if(array_key_exists("valueLabel", $infoItem))
                      <a href="{{$infoItem["value"]}}" target="_blank" class="underline">
                        {{$infoItem["valueLabel"]}}
                      </a>
                    @elseif(preg_match("/^https?:\/\//i", $infoItem["value"]))
                      <a href="{{$infoItem["value"]}}" target="_blank" class="underline">
                        {{$infoItem["value"]}}
                      </a>
                    @else
                      {{$infoItem["value"]}}
                    @endif
                  </td>
                </tr>

              @empty
                <tr>
                  <td class="italic text-gray-dark text-center" colspan="2">No Information</td>
                </tr>
              @endforelse
            </table>
          </div>
        </div>
      </div>
    </div>
    <span hidden class="text-md font-semibold" id="coordinate-location-title">Coordinate Location</span>
    <div hidden id="entity-map" style="height: 400px" class="mt-5"></div>
  </div>

</div>
