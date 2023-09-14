<div class="relative mx-2">
  <span class="text-lg font-semibold">Part Of</span>
  <button class="collapse_button absolute top-0 right-0 transition-transform duration-400 transform hover:text-gray-dark">
    @svg('heroicon-s-chevron-up', 'h-7 w-7')
  </button>
  <div class="relative overflow-hidden transition-max-h ease-out duration-300">
    <div class="mt-4  text-base">
      <div class="mb-4 flex flex-row justify-start">
        @if($isPartOf != null)
          <div class="w-2/6">Part Of</div>
          <div class="w-4/6 font-thin">
            <a href="{{ env('APP_URL') . '/musicalWork/' . $isPartOfUuid }}" class="underline">
              {{ $isPartOf }}
            </a>
          </div>
        @else
          <div class="w-2/6">Part Of</div>
          <div class="w-4/6 font-thin text-gray">No data available</div>
        @endif
      </div>
    </div>
  </div>
</div>
